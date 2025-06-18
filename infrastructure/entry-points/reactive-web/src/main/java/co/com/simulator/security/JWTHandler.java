package co.com.simulator.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JWTHandler {

    public static String SECRET_KEY = "jkflasdkflasdFKLASFSFLJS1651651f6asdfasdfsdfs";

    public Mono<String> generateJwtWithUserName(String userName) {
        return Mono.fromCallable(() ->
                Jwts.builder()
                        .subject(userName)
                        .issuedAt(Date.from(Instant.now()))
                        .expiration(Date.from(Instant.now().plusSeconds(3600)))
                        .signWith(getSecretKey())
                        .compact());
    }

    public Mono<String> getUserNameOfJwt(String jwt) {
        return Mono.fromCallable(() ->
                Jwts.parser()
                        .verifyWith(getSecretKey())
                        .build()
                        .parseSignedClaims(jwt)
                        .getPayload()
                        .getSubject());
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
