package social.media.network.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiredTimeAccess}")
    private long expiredTimeAccess;

    @Value("${jwt.expiredTimeRefresh}")
    private long expiredTimeRefress;


    public String createToken(String userEmail,long time) {
        try {
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            Claims claim = Jwts.claims().setSubject(userEmail);
            claim.put("roles", roles);
            Date now = new Date();
            Date expiration = new Date(now.getTime() + time);
            return Jwts.builder().setClaims(claim).setIssuedAt(now)
                    .setExpiration(expiration).signWith(SignatureAlgorithm.HS256, secretKey).compact();
        } catch (Exception e) {
            return "1";
        }
    }

    public String createAccessToken(String userEmail) {
        return createToken(userEmail,expiredTimeAccess);
    }

    public String createRefreshToken(String userEmail) {
        return createToken(userEmail,expiredTimeRefress);
    }

    public String getUserEmail(String token) {
        try{

            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return null;
        }
    }

    public Date getExpiration(String token) {
        try{
            return Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody().getExpiration();
        }catch (Exception e){
            return null;
        }

    }


}
