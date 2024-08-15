package com.example.application;

import io.jsonwebtoken.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username) {
        try {
            // -------------------------------------------------------------------------------
            // HEADER
            // -------------------------------------------------------------------------------
            // The JWT signature algorithm we will be using to sign the token
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

            // Creating the Header of
            HashMap<String, Object> header = new HashMap<String, Object>();
            header.put("alg", signatureAlgorithm.toString()); // HS256
            header.put("typ", "JWT");
            // -------------------------------------------------------------------------------
            // CREATING TOKEN + ADDING HEADER
            // -------------------------------------------------------------------------------
            // Generate tokenJWT + adding header
            JwtBuilder tokenJWT = Jwts.builder()
                    .setHeader(header)
                    .claim("username", username)
                    .claim("roles", List.of("ROLE_ADMIN", "ROLE_GUEST"))
                    .issuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                    .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)));

            // -------------------------------------------------------------------------------
            // CREATING TOKEN + ADDING HEADER
            // -------------------------------------------------------------------------------
            // Compact the tokenJWT + save the value in tokenJWTString
            String tokenJWTString = tokenJWT.compact();
            System.out.println(tokenJWTString);
            // Response to Request from Controller
            return tokenJWTString;
        } catch (Exception e) {
            System.out.println(e);
            return "Error creating the token JWT" + e;
        }
    }
}

   /* @Value("${jwt.secret}")
   private String secretKey;
   private static final long EXPIRATION_TIME = 864_000_000; // 10 days

   private Key key;

   @PostConstruct
   public void init(){
       byte[] keyBytes = Decoders.BASE64.decode(secretKey);
       key = Keys.hmacShaKeyFor(keyBytes);
   }

   public String generateToken(String username) {
       return Jwts.builder()
               .subject(username)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
               .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
               .compact();
   }

   public String extractUsername(String token) {
       try {
           return Jwts.parser()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token)
                   .getBody()
                   .getSubject();
       } catch (JwtException e) {
           // Manejar excepción, por ejemplo, token inválido
           return null;
       }
   }

   public  boolean validateToken(String token) {
       try {
           Jwts.parser()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token);
           return true;
       } catch (Exception e) {
           return false;
       }
   }

   public String getUsernameFromToken(String token){
       return getClaim(token, Claims::getSubject);
   }

   public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
       Claims claims = extractAllClaims(token);
       return claimsTFunction.apply(claims);
   }

   public Claims extractAllClaims(String token){
       return Jwts.parser()
               .setSigningKey(key)
               .build()
               .parseClaimsJws(token)
               .getBody();
   }
   public Key getKey() {
       return key;
   }*/
