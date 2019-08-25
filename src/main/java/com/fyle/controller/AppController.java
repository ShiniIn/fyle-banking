package com.fyle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyle.model.Branch;
import com.fyle.service.BranchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AppController {
  
  @Autowired
  BranchManager branchManager;
  
  @RequestMapping(value = "/getBranch", method = RequestMethod.GET)
  @CrossOrigin(value = "*", maxAge = 3600)
  public Object getBranch(HttpServletRequest request, HttpServletResponse res, @RequestParam(name = "Ifsc") String ifsc) {
    Branch branch = branchManager.getBranchDetails(ifsc);
    return branch;
  }
  
  @RequestMapping(value = "/getBranches", method = RequestMethod.GET)
  @CrossOrigin(value = "*", maxAge = 3600)
  public Object getBranches(HttpServletRequest request, HttpServletResponse res, @RequestParam(name = "bankName") String bankName,@RequestParam(name = "city") String city,@RequestParam(name = "limit") int limit,@RequestParam(name = "offSet") int offSet) {
    List<Branch> branches = branchManager.getBranches(bankName,city,limit,offSet);
    return branches;
  }
  
}
