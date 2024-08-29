package social.media.network.services.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import social.media.network.payload.dto.request.ProfileRequest;
import social.media.network.payload.dto.response.ProfileResponse;
import social.media.network.entity.User.User;
import social.media.network.exception.custorm_exception.CustormException;
import social.media.network.repository.UserRepo;
import social.media.network.services.ProfileServices;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileServicesImpl implements ProfileServices {
    private final UserRepo userRepo;
    private final AuthServicesImpl authServicesImpl;
    @Override
    public ProfileResponse takeProfile(Long idProfile) {
        ProfileResponse profileResponse = new ProfileResponse();
        //User xem Profile <--> userView , Profile cua User <--> userProfile
        User userView = authServicesImpl.getAuth();

        User userProfile = userRepo.findById(idProfile).orElseThrow(() -> new CustormException("Profile not found"));

        authServicesImpl.checkDelete(userProfile);

        authServicesImpl.checkBlock(userView,userProfile);

        BeanUtils.copyProperties(userProfile,profileResponse);

        // check idUser view profile minh <--> trung thi set public hay private van xem duoc
        // user de profile public xem duoc profile User other
        boolean check = ((userView.getUserId().equals(idProfile)) || (userProfile.getIsProfilePublic().equals(true)));

        return check ? profileResponse : null;
    }

    @Override
    public Boolean accessProfile() {
        User user = authServicesImpl.getAuth();
        user.setIsProfilePublic(user.getIsProfilePublic() ? false : true);
        userRepo.save(user);
        return true;
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest profileRequest) {
        ProfileResponse profileResponse = new ProfileResponse();
        User user = authServicesImpl.getAuth();
        BeanUtils.copyProperties(profileRequest,user);
        userRepo.save(user);
        BeanUtils.copyProperties(user,profileResponse);
        return profileResponse;
    }


}
