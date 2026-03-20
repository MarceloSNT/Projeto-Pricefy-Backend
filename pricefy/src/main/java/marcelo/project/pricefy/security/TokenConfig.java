package marcelo.project.pricefy.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import marcelo.project.pricefy.entity.UserModel;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    private String secret = "secret";

    public String generateToken(UserModel user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("idUser", user.getIdUser())
                .withClaim("dsUsername", user.getDsUsername())
                .withSubject(user.getDsEmail())
                .withIssuedAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decoded = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .idUser(decoded.getClaim("idUser").asLong())
                    .dsUsername(decoded.getClaim("dsUsername").asString())
                    .build());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
