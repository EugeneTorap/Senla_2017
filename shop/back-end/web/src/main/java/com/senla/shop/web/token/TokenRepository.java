package com.senla.shop.web.token;

import com.senla.shop.model.Reader;

import java.util.HashMap;
import java.util.Map;

public class TokenRepository {
    private Map<String, Reader> tokens;
    static private TokenRepository instance;

    private TokenRepository() {
        tokens = new HashMap<>();
    }

    public static TokenRepository getInstance() {
        if (instance == null) {
            instance = new TokenRepository();
        }
        return instance;
    }



    public boolean isTokenExist(String token) {
        return tokens.containsKey(token);
    }

    public void putToken(String token, Reader reader) {
        tokens.put(token, reader);
    }

    public void removeToken(String token) {
        tokens.remove(token);
    }

    public Reader getUserByToken(String token) {
        return tokens.get(token);
    }
}
