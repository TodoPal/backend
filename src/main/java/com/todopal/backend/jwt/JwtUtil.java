package com.todopal.backend.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.todopal.backend.exceptions.UserNotFoundException;
import com.todopal.backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtUtil {
    // get the encoded secret key
    @Value("${jwt.token.secret}")
    private String secret;

    private final String issuer = "todo-pal:auth-service";

    private final UserService userService;

    public JwtUtil(UserService userService) {
        this.userService = userService;
    }

    public String getJwtToken(String username) {
        return JWT.create()
                .withClaim("username", username)
                .withIssuer(this.issuer)
                .withIssuedAt(Date.from(Instant.now()))
                // the token will expire after a month
                .withExpiresAt(Date.from(Instant.now().plus(30, ChronoUnit.DAYS)))
                .sign(getSigningAlg());
    }

    public void verifyJwtToken(String token) {
        this.verifyUserExists(token);
        var verifier = JWT.require(getSigningAlg())
                .withIssuer(this.issuer)
                .build();
        verifier.verify(token);
    }

    private Algorithm getSigningAlg() {
        return Algorithm.HMAC256(Base64.getDecoder().decode(this.secret));
    }

    private void verifyUserExists(String token) {
        var username = JWT.decode(token).getClaim("username").toString().replace("\"", "");
        if (!this.userService.isUserExists(username)) {
            var msg = MessageFormat.format("User: {0} not found.", username);
            log.error(msg);
            throw new UserNotFoundException(msg);
        }
    }
}
