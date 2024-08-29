package social.media.network.config.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import social.media.network.config.security.CustomUserDetailsService;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.repository.InvalidTokenRepo;
import social.media.network.repository.UserRepo;

import java.io.IOException;
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenService jwtTokenService;
    private final CustomUserDetailsService userDetailsService;
    private final InvalidTokenRepo invalidTokenRepo;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null && invalidTokenRepo.findById(token).stream().count()==0) {
            String userEmail = jwtTokenService.getUserEmail(token);
            if (userEmail != null) {
                Boolean is_delete = userRepo.findByUserEmail(userEmail).getIsDelete();
                if(is_delete.equals(false)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                    //FAKE AUTHENTICATION
                    SecurityContextHolder.getContext()
                            .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,
                                    userDetails.getPassword(), userDetails.getAuthorities()));
                }else{
                    throw new CustormException("User is deleted");
                }
            }else{
                throw new CustormException("Token is expiration");
            }
        }
        //cho qua
        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        System.out.println(bearerToken);
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ? bearerToken.substring(7) : null;
    }
}
