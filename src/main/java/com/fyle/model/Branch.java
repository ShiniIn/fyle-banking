package com.fyle.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch {
  
  @Id
  @Column(name = "ifsc", updatable = false, nullable = false)
  String ifsc;
  @Column(name = "bank_Id")
  Integer bank_id;
  @Column(name = "branch")
  String branch;
  @Column(name = "address")
  String address;
  @Column(name = "city")
  String city;
  @Column(name = "district")
  String district;
  @Column(name = "state")
  String state;
  
  public Branch() {
  }
  
  public String getIfsc() {
    return ifsc;
  }
  
  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }
  
  public Integer getBank_id() {
    return bank_id;
  }
  
  public void setBank_id(Integer bank_id) {
    this.bank_id = bank_id;
  }
  
  public String getBranch() {
    return branch;
  }
  
  public void setBranch(String branch) {
    this.branch = branch;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public String getDistrict() {
    return district;
  }
  
  public void setDistrict(String district) {
    this.district = district;
  }
  
  public String getState() {
    return state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
}
