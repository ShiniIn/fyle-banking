package com.fyle.dao;

import com.fyle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
  
  @Query("from User where  user_name=?1")
  User findByUsername(String name);
  
  @Query("from User where  user_name=?1 and pass_key=?2")
  User findUser(String name, String password);
  
}


