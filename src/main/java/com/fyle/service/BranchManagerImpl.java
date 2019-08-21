package com.fyle.service;

import com.fyle.dao.BankDao;
import com.fyle.dao.BranchDao;
import com.fyle.exception.ResourceNotFoundException;
import com.fyle.model.Bank;
import com.fyle.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BranchManagerImpl")
public class BranchManagerImpl implements BranchManager {
  
  @Autowired
  BranchDao branchDao;
  @Autowired
  BankDao bankDao;
  
  @Override
  public Branch getBranchDetails(String ifsc) {
    Branch branch = branchDao.findByIfsc(ifsc);
    if (branch == null) {
      throw new ResourceNotFoundException("There does not exist any branch with given ifsc");
    }
    return branch;
  }
  
  @Override
  public List<Branch> getBranches(String bankName, String city, int limit, int offSet) {
    Bank bank = bankDao.findBankByName(bankName);
    if (bank == null) {
      throw new ResourceNotFoundException("No bank is available with this bank name");
    } else {
      Integer bank_id = bank.getId();
      List<Branch> branches = branchDao.getBranches(city, bank_id);
      List<Branch> paginated = branches.subList(
        Math.min(branches.size(), offSet),
        Math.min(branches.size(), offSet + limit));
      return paginated;
    }
  }
}
