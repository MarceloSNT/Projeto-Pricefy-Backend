package marcelo.project.pricefy.security;

import lombok.Builder;

@Builder
public record JWTUserData(
    Long idUser,
    String dsUsername,
    String dsEmail,
    String dsPassword
){}
