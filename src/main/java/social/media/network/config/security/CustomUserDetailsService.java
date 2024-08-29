package social.media.network.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import social.media.network.entity.User.User;
import social.media.network.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = userRepo.findByUserEmail(useremail);
        if(user == null) throw new UsernameNotFoundException("User not found");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), authorities);
        }
    }

