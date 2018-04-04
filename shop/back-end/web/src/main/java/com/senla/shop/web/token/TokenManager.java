package com.senla.shop.web.token;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenManager {
    private static final String KEY = "eugene";

    public static String generateToken(String login, String password) throws Exception {
        if (login == null || password == null)
            return null;
        IFacade facade = Facade.getInstance();
        Reader reader = facade.getByLogin(login);
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(reader.getPassword())) {
            tokenData.put("clientType", "user");
            tokenData.put("userID", reader.getId());
            tokenData.put("username", login);
            tokenData.put("token_create_date", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, 1);
            tokenData.put("token_expiration_date", calendar.getTime());

            JwtBuilder jwtBuilder = Jwts.builder();

            jwtBuilder.setExpiration(calendar.getTime());

            jwtBuilder.setClaims(tokenData);

            return jwtBuilder.signWith(SignatureAlgorithm.HS512, KEY).compact();
        } else {
            throw new Exception("Authentication error");
        }
    }

    public static boolean validateToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(token)
                .getBody();
        Date expirationDate = new Date((Long) claims.get("token_expiration_date"));
        Date now = new Date();
        return !expirationDate.before(now);
    }
}
