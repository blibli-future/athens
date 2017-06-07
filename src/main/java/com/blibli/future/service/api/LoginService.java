package com.blibli.future.service.api;

public interface LoginService {
    //TODO: Change the exception into a more meaningful one
    String createNewToken(String username, String password) throws Exception;
}
