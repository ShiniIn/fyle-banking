package com.fyle.service;

import com.fyle.model.Branch;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BranchManager {
  
  @Transactional
  Branch getBranchDetails(String ifsc);
  
  @Transactional
  List<Branch> getBranches(String bankName, String city, int limit, int offSet);
}
