package com.josh.usersrestapi.utility;

import com.josh.usersrestapi.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Tejashree Surve
 * @Purpose : This is Jwt Token Utility.
 */
@Component
public class JwtTokenUtil {

    SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    static String secretKey = "secret";

    /**
     * Generate token for User Email.
     * @param email User email.
     * @return generate token in String format.
     */
    public String generateToken(String email){
        Date date = new Date();
        return Jwts.builder().setId(email).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000))).
                signWith(algorithm,secretKey).compact();
    }

    /**
     * Get All Claims For given token.
     * @param token User token.
     * @return User Email in String object.
     */
    public String getAllClaims(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch(Exception e){
            throw new InvalidTokenException(MessageInfo.Invalid_Token);
        }
        return claims.getId();
    }
}
