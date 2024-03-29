package com.fyle.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
  
  @Autowired
  JwtProperties jwtProperties;
  
  @Autowired
  private UserDetailsService userDetailsService;
  
  private String secretKey;
  
  @PostConstruct
  protected void init() throws IOException {
    secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
  }
  
  public String createToken(String username) {
    
    Claims claims = Jwts.claims().setSubject(username);
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtProperties.getValidityInMs());
    return Jwts.builder()//
      .setClaims(claims)//
      .setIssuedAt(now)//
      .setExpiration(validity)//
      .signWith(SignatureAlgorithm.HS256, secretKey)//
      .compact();
  }
  
  public Authentication getAuthentication(String token) {
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", null);
  }
  
  public String getUsername(String token) {
    String userName = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    return userName;
  }
  
  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
  
  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      
      if (claims.getBody().getExpiration().before(new Date())) {
        return false;
      }
      
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
    }
  }
  
}
