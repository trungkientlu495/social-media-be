package social.media.network.services.Impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import social.media.network.config.security.jwt.JwtTokenService;
import social.media.network.entity.User.Block;
import social.media.network.entity.User.InvalidToken;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.payload.MessageResponse;
import social.media.network.payload.dto.request.*;
import social.media.network.payload.mapping.RegisterMapper;
import social.media.network.repository.BlockRepo;
import social.media.network.repository.InvalidTokenRepo;
import social.media.network.repository.UserRepo;
import social.media.network.services.AuthServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServicesImpl implements AuthServices {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepo userRepo;
    private final EmailServicesImpl emailServices;
    private final RedisTemplate<String, Integer> redisTemplate,redisForgotPassword;
    private final InvalidTokenRepo invalidTokenRepo;
    private final BlockRepo blockRepo;

    @Override
    public Object login(LoginRequest loginDTO) {
        try{
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserEmail()
                            , loginDTO.getPassword()));
            checkDelete(userRepo.findByUserEmail(loginDTO.getUserEmail()));
            String accessToken = jwtTokenService.createAccessToken(loginDTO.getUserEmail());
            String refreshToken = jwtTokenService.createRefreshToken(loginDTO.getUserEmail());
            Map<String,Object> map = new HashMap<>();
            map.put("accessToken", accessToken);
            map.put("refreshToken", refreshToken);
            return map;
        }catch (Exception e){
            return "Login failed";
        }
    }

    //luu thong tin user vao redis
    // dung smtp -> send otp gmail -> user
    @Override
    public MessageResponse registerUser(RegisterRequest register) {
        User user = userRepo.findByUserEmail(register.getUserEmail());
        if (user != null) return new MessageResponse("Email already exist");
        emailServices.sendEmailRegister(register.getUserEmail(),"XÁC MINH EMAIL","Mã OTP của bạn là: ");
        return new MessageResponse("OTP send to email");
    }

    // check otp user enter
    @Override
    public MessageResponse registerCheckOTP(RegisterRequest register) {
        User user1 = userRepo.findByUserEmail(register.getUserEmail());
        if (user1 != null) return new MessageResponse("Email already exist");
        Integer otp = redisTemplate.opsForValue().get(register.getUserEmail());
        if (register.getOtp().equals(otp)) {
            redisTemplate.delete(register.getUserEmail());
            User user = RegisterMapper.INSTANCE.toUser(register);
            user.setPassword(new BCryptPasswordEncoder().encode(register.getPassword()));
            userRepo.save(user);
            return new MessageResponse("Sign up for success");
        }
        return new MessageResponse("invalid otp");
    }

    @Override
    public MessageResponse logOut(HttpServletRequest request) {
        try{
            String accessToken = request.getHeader("Authorization").substring(7);
            log.info("mnpq: "+jwtTokenService.getExpiration(accessToken));
            InvalidToken invalidToken = new InvalidToken();
            invalidToken.setToken(accessToken);
            invalidToken.setExpiryDate(
                    jwtTokenService.getExpiration(accessToken)
            );
            invalidTokenRepo.save(invalidToken);
            return new MessageResponse("Log out success");
        }catch (Exception e){
            log.error(e.getMessage());
            return new MessageResponse("Log out failed");
        }

    }

    @Override
    public MessageResponse deleteAccount(AccountRequest accountDTO) {
            User user = getAuth();
            if(new BCryptPasswordEncoder().matches(accountDTO.getPasswordAccount(), user.getPassword())){
                user.setIsDelete(true);
                userRepo.save(user);
                return new MessageResponse("Account deleted successfully");
            } else throw new CustormException("Delete invalid");
    }

    @Override
    public MessageResponse forgotPassword(ForgotPasswordRequest forgotPasswordDTO) {
        User user = userRepo.findByUserEmail(forgotPasswordDTO.getUserEmail());
        if(user == null) {
            throw new CustormException("Email not found");
        }
        emailServices.sendEmailForgot(user.getUserEmail(),"a","b");
        return new MessageResponse("OTP send to email");
    }

    @Override
    public MessageResponse forgotPasswordOTP(ForgotPasswordRequest forgotPasswordDTO) {
        User user = userRepo.findByUserEmail(forgotPasswordDTO.getUserEmail());
        if(user == null) {
            throw new CustormException("Email not found");
        }
        Integer otp = redisForgotPassword.opsForValue().get(forgotPasswordDTO.getUserEmail());
        if(forgotPasswordDTO.getOtp().equals(otp)) {
            redisForgotPassword.delete(forgotPasswordDTO.getUserEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(forgotPasswordDTO.getNewPassword()));
            userRepo.save(user);
            return new MessageResponse("Update Password success");
        }
        throw new CustormException("Input OTP validation failed");
    }

    @Override
    public MessageResponse changePassword(ChangePasswordRequest changePasswordDTO) {
        User user = getAuth();
        if(new BCryptPasswordEncoder().matches(changePasswordDTO.getOldPassword(),user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(changePasswordDTO.getNewPassword()));
            userRepo.save(user);
            return new MessageResponse("Update Password success");
        }
        return new MessageResponse("Password does not match");
    }

    public User getAuth() {
        Object userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail = null;
        if(userDetail == null) throw new CustormException("User not authenticated");
        if(userDetail instanceof org.springframework.security.core.userdetails.User) {
            userEmail = ((org.springframework.security.core.userdetails.User) userDetail).getUsername();
        }
        if(userEmail==null) throw new CustormException("User not found");
        User user = userRepo.findByUserEmail(userEmail);
        if(user==null) throw new CustormException("User not found");
        return user;
    }

    //check User1 block User2 chua va nguoc lai
    public void checkBlock(User user1,User user2) {
        List<Block> block1s = blockRepo.findByBlockedUser(user1.getUserId());
        List<Block> block2s = blockRepo.findByBlockedUser(user2.getUserId());
        block1s.addAll(block2s);
        if(block1s!=null) {
            List<Block> block3s = block1s.stream().filter(block -> block.getBlockingUser()
                    .getUserId().equals(user1.getUserId())
                    || block.getBlockingUser().getUserId().equals(user2.getUserId())).toList();
            if(!block3s.isEmpty()) {
                throw new CustormException("Blocked user");
            }
        }
    }

    public void checkDelete(User user) {
        if(user.getIsDelete().equals(true))
            throw new CustormException("User is deleted");
    }
}
