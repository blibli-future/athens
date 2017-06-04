package com.blibli.future.service.api;

public interface AuthenticationService {
    //TODO: Change the exception into a more meaningful one
    String createToken(String username, String password) throws Exception;
}
