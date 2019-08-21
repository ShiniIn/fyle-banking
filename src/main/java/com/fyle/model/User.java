package com.fyle.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  
  @Id
  @Column(name = "userId", updatable = false, nullable = false)
  Integer user_id;
  @Column(name = "userName")
  String user_name;
  @Column(name = "pass_key")
  String pass_key;
  
  public Integer getUser_id() {
    return user_id;
  }
  
  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }
  
  public String getUser_name() {
    return user_name;
  }
  
  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }
  
  public String getPass_key() {
    return pass_key;
  }
  
  public void setPass_key(String pass_key) {
    this.pass_key = pass_key;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
  
  @Override
  public String getPassword() {
    return pass_key;
  }
  
  @Override
  public String getUsername() {
    return user_name;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
