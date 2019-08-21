package com.fyle.dao;

import com.fyle.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchDao extends JpaRepository<Branch, Long> {
  
  Branch findByIfsc(String ifsc);
  
  @Query("from Branch where city=?1 and bank_id=?2")
  List<Branch> getBranches(String city, int bank_id);
}
