package social.media.network.services;

import social.media.network.payload.dto.request.ProfileRequest;
import social.media.network.payload.dto.response.ProfileResponse;

public interface ProfileServices {
    ProfileResponse takeProfile(Long idProfile);

    Boolean accessProfile();

    ProfileResponse updateProfile(ProfileRequest profileRequest);
}
