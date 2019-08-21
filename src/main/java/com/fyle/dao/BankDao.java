package com.fyle.dao;

import com.fyle.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDao extends JpaRepository<Bank, Long> {
  
  Bank findBankByName(String name);
  
}


