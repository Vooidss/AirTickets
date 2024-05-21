package org.AirTickets.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.AirTickets.security.UsersDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Component
public class JWTutil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String login){
        Date exparationDate = Date.from(ZonedDateTime.now().plusDays(5).toInstant());

        return JWT.create()
                .withSubject("Login details")
                .withClaim("login",login)
                .withIssuedAt(new Date())
                .withIssuer("AirTickets")
                .withExpiresAt(exparationDate)
                .sign(Algorithm.HMAC256(secret));
    }



    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("Login details")
                .withIssuer("AirTickets")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("login").asString();
    }
}
