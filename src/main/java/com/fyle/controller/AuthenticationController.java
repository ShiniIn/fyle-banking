package com.fyle.controller;

import com.fyle.dao.UserDao;
import com.fyle.model.User;
import com.fyle.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.GET)
public class AuthenticationController {
  
  @Autowired
  JwtTokenProvider jwtTokenProvider;
  @Autowired
  UserDao userDao;
  
  @PostMapping(value = "/signin")
  public ResponseEntity signIn(HttpServletRequest request, HttpServletResponse res, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
    try {
      User user = userDao.findUser(username, password);
      if (user == null) {
        throw new BadCredentialsException("Invalid username/password supplied");
      }
      String token = jwtTokenProvider.createToken(username);
      Map<Object, Object> model = new HashMap<>();
      model.put("username", username);
      model.put("token", token);
      return ok(model);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username/password supplied");
    }
  }
}
