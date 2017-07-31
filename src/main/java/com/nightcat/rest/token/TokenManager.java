package com.nightcat.rest.token;

import com.nightcat.entity.Token;

/**
 * @author Aollio
 * @date 15/05/2017
 */
public interface TokenManager {
    Token createToken(String uid);

    boolean checkToken(Token tokenModel);

    Token getToken(String authentication);

    void delToken(String uid);

}
