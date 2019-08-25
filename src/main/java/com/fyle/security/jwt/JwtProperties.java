package com.fyle.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
  
  private String secretKey;
  
  //validity in milliseconds
  private long validityInMs = 432000000; // 5days
  
  public String getSecretKey() throws IOException {
    Properties properties = new Properties();
    InputStream is = this.getClass().getResourceAsStream("/application.properties");
    properties.load(is);
    secretKey = properties.getProperty("secretKey");
    return secretKey;
  }
  
  public long getValidityInMs() {
    return validityInMs;
  }
}
