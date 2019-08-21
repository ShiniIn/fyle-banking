package com.fyle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyle.graphql.SchemaGraphQl;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BranchController {
  
  @Autowired
  SchemaGraphQl schemaGraphQl;
  
  @RequestMapping(value = "/graphql", method = RequestMethod.GET)
  @CrossOrigin(value = "*", maxAge = 3600)
  public String executeQuery(HttpServletRequest request, HttpServletResponse res) throws IOException {
    String query = request.getParameter("query");
    Object data = execute(query, res);
    return data.toString();
  }
  
  @RequestMapping(value = "/graphql", method = RequestMethod.POST, consumes = {"application/json", "application/x-www-form-urlencoded"}, produces = "application/json;charset=UTF-8")
  @CrossOrigin(value = "*", maxAge = 3600)
  public String executeMutation(HttpServletRequest request, HttpServletResponse res, @RequestBody String body) throws IOException {
    String query = request.getParameter("query");
    System.out.println("I am here" + body);
    if (query == null) {
      ObjectMapper mapper = new ObjectMapper();
      Map reqMap = mapper.readValue(body, Map.class);
      if (reqMap != null) {
        query = (String) reqMap.get("query");
      }
    }
    Object data = execute(query, res);
    if (data != null) {
      return data.toString();
    } else {
      return null;
    }
  }
  
  private Object execute(String query, HttpServletResponse res) throws IOException {
    try {
      Map<String, Object> data = null;
      List<GraphQLError> graphQLErrors = new ArrayList<>();
      String json = null;
      GraphQLSchema schema = schemaGraphQl.schema();
      GraphQL graphQL = GraphQL.newGraphQL(schema).build();
      ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
      ExecutionResult executionResult = graphQL.execute(executionInput);
      data = executionResult.toSpecification();
      graphQLErrors = executionResult.getErrors();
      ObjectMapper objectMapper = new ObjectMapper();
      res.setHeader("Content-Type", "application/json;charset=UTF-8");
      if (graphQLErrors.size() > 0) {
        json = objectMapper.writeValueAsString(graphQLErrors);
        res.setStatus(400);
        return json;
      } else {
        json = objectMapper.writeValueAsString(data);
        return json;
      }
    } catch (Exception e) {  // TODO
      //@ingore
      return null;
    }
  }
}
