package com.fyle.exception;

import graphql.ExceptionWhileDataFetching;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class ExceptionHandler implements DataFetcherExceptionHandler {
  
  @Override
  public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters handlerParameters) {
    Throwable exception = handlerParameters.getException();
    SourceLocation sourceLocation = handlerParameters.getSourceLocation();
    ExecutionPath path = handlerParameters.getPath();
    
    ExceptionWhileDataFetching error = new ExceptionWhileDataFetching(path, exception, sourceLocation);
    return null;
  }
  
}