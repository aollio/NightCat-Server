package com.yemao.users.service;


import com.yemao.users.models.Token;

/**
 * @author Aollio
 * @date 15/05/2017
 */
public interface TokenService {
    Token createToken(String uid);

    boolean checkToken(Token tokenModel);

    Token getToken(String authentication);

    void delToken(String uid);

}
