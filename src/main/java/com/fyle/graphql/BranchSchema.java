package com.fyle.graphql;

import graphql.Scalars;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import static graphql.schema.GraphQLList.list;

@Component
public class BranchSchema {
  
  BranchFetcher branchFetcher;
  
  public BranchSchema(BranchFetcher branchFetcher) {
    this.branchFetcher = branchFetcher;
  }
  
  public void schema(GraphQLObjectType.Builder queryType, GraphQLObjectType.Builder mutationType) {
    
    GraphQLObjectType bank = branchSchema();
    
    GraphQLFieldDefinition.Builder readBranch = GraphQLFieldDefinition.newFieldDefinition().name("getBranchByIFSC").description("This API returns the bank details by IFSC code");
    readBranch.type(bank);
    readBranch.argument(GraphQLArgument.newArgument().name("ifsc").type(Scalars.GraphQLString).build());
    readBranch.dataFetcher(branchFetcher);
    queryType.field(readBranch);
    
    GraphQLFieldDefinition.Builder getBranches = GraphQLFieldDefinition.newFieldDefinition().name("getBankBranchesByCity").description("This API returns the bank list with details by city and name");
    getBranches.type(list(bank));
    getBranches.argument(GraphQLArgument.newArgument().name("bankName").type(GraphQLNonNull.nonNull(Scalars.GraphQLString)).build());
    getBranches.argument(GraphQLArgument.newArgument().name("city").type(GraphQLNonNull.nonNull(Scalars.GraphQLString)).build());
    getBranches.argument(GraphQLArgument.newArgument().name("limit").type(Scalars.GraphQLInt).build());
    getBranches.argument(GraphQLArgument.newArgument().name("offSet").type(Scalars.GraphQLInt).build());
    getBranches.dataFetcher(branchFetcher);
    mutationType.field(getBranches);
  }
  
  GraphQLObjectType branchSchema() {
    GraphQLObjectType.Builder objectType = GraphQLObjectType.newObject().name("Branch").description("It define the object structure for the Definition of the DataType");
    this.addField(objectType, "ifsc", "This defines the ifsc code of the bank branch", Scalars.GraphQLString);
    this.addField(objectType, "bankId", "This defines the bank id of the bank branch", Scalars.GraphQLInt);
    this.addField(objectType, "branch", "This defines the name of the bank branch", Scalars.GraphQLString);
    this.addField(objectType, "address", "This defines the address of the bank branch", Scalars.GraphQLString);
    this.addField(objectType, "city", "This defines the city of the bank branch", Scalars.GraphQLString);
    this.addField(objectType, "district", "This defines the district of the bank branch", Scalars.GraphQLString);
    this.addField(objectType, "state", "This defines the state of the bank branch", Scalars.GraphQLString);
    return objectType.build();
  }
  
  public void addField(GraphQLObjectType.Builder objectType, String fieldName, String description, GraphQLOutputType type) {
    GraphQLFieldDefinition.Builder field = GraphQLFieldDefinition.newFieldDefinition().name(fieldName).description(description).type(type);
    objectType.field(field);
  }
}
