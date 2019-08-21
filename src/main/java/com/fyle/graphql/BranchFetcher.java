package com.fyle.graphql;

import com.fyle.model.Branch;
import com.fyle.service.BranchManager;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class BranchFetcher implements DataFetcher {
  
  @Autowired
  BranchManager branchManager;
  
  @Override
  public Object get(DataFetchingEnvironment environment) throws Exception {
    if (environment.getFieldDefinition().getName().equals("getBranchByIFSC")) {
      String ifsc = environment.getArgument("ifsc");
      Branch branch = branchManager.getBranchDetails(ifsc);
      HashMap<String, Object> bankMap = convertToMap(branch);
      return bankMap;
    }

    if (environment.getFieldDefinition().getName().equals("getBankBranchesByCity")) {
      String bankName = environment.getArgument("bankName");
      String city = environment.getArgument("city");
      Integer offSet = environment.getArgument("offSet") != null ? environment.getArgument("offSet") : 0;
      Integer limit = environment.getArgument("limit") != null ? environment.getArgument("limit") : 10;
      List<Branch> branches = branchManager.getBranches(bankName, city, limit, offSet);
      List<HashMap<String, Object>> bankList = new ArrayList<>();
      for (Branch branch : branches) {
        bankList.add(convertToMap(branch));
      }
      return bankList;
    }
    
    return null;
  }
  
  public HashMap<String, Object> convertToMap(Branch branch) {
    HashMap<String, Object> map = new HashMap() {{
      put("ifsc", branch.getIfsc());
      put("bankId", branch.getBank_id());
      put("branch", branch.getBranch());
      put("address", branch.getAddress());
      put("city", branch.getCity());
      put("district", branch.getDistrict());
      put("state", branch.getState());
    }};
    return map;
  }
  
}
