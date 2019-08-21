package com.fyle.graphql;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.stereotype.Component;

@Component("graphQlSchema")
public class SchemaGraphQl {

  BranchSchema branchSchema;

  public SchemaGraphQl(BranchSchema branchSchema) {
    this.branchSchema = branchSchema;
  }

  public GraphQLSchema schema() {
    GraphQLObjectType.Builder queryType = GraphQLObjectType.newObject().name("Query").description("This defines the global structure for the Query Object");
    GraphQLObjectType.Builder mutationType = GraphQLObjectType.newObject().name("Mutation").description("This defines the global structure for the Mutation Object");

    branchSchema.schema(queryType, mutationType);

    GraphQLSchema schema = GraphQLSchema.newSchema().query(queryType.build()).mutation(mutationType.build()).build();
    return schema;
  }
}
