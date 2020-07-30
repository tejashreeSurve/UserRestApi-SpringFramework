package com.josh.usersrestapi.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Tejashree Surve
 * @Purpose : This is Jwt Token.
 */
@Component
public class JwtToken {
    @Autowired
    MessageInfo messageInfo;

    SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    static String secretKey = "secret";

    // Generate Token
    public String generateToken(String email){
        return Jwts.builder().setId(email).setIssuedAt(new Date(System.currentTimeMillis())).
                signWith(algorithm,secretKey).compact();
    }

    // Get Token
    public String getToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        }catch(Exception e){
                System.out.println(e+ messageInfo.Invalid_Token);
        }
        return claims.getId();
    }

    // Refresh Token
    public void refreshToken(String token){
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        try {
            claims.setExpiration(new Date(System.currentTimeMillis() + 2000));
        }catch(Exception e){
            System.out.println("Jwt:"+e);
        }
    }

}
