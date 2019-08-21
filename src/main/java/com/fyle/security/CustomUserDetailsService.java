package com.fyle.security;

import com.fyle.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
  
  private UserDao users;
  
  public CustomUserDetailsService(UserDao users) {
    this.users = users;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      return this.users.findByUsername(username);
    } catch (Exception ex) {
      throw new UsernameNotFoundException("Username: " + username + " not found");
    }
  }
}
