package com.CashReportSystem.service;

import com.CashReportSystem.exception.NoSuchTokenException;
import com.CashReportSystem.exception.NoSuchUserException;
import com.CashReportSystem.helper.TokenHelper;
import com.CashReportSystem.model.User;
import com.CashReportSystem.repository.TokenRepository;
import com.CashReportSystem.repository.UserRepository;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import javax.servlet.http.Cookie;

@Service
public class TokenService {

    private final TokenRepository tokenRepo;
    private final UserRepository userRepository;
    private final TokenHelper tokenhelper;

    public TokenService(TokenRepository tokenRepo, UserRepository userRepository, TokenHelper tokenhelper) {
        this.tokenRepo = tokenRepo;
        this.userRepository = userRepository;
        this.tokenhelper = tokenhelper;
    }

    public boolean validateToken(String tokenJsonObject) throws NoSuchTokenException {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);
        String token = tokenJson.getString("token");

        if (tokenRepo.findByToken(token).isEmpty()) {
            throw new NoSuchTokenException("Token is not valid!");
        } else {
            return true;
        }
    }

    public boolean validateTokenString(String token) throws NoSuchTokenException {
        if (tokenRepo.findByToken(token).isEmpty()) {
            throw new NoSuchTokenException("Token is not valid!");
        } else {
            return true;
        }
    }

    public String findUserPermission(String tokenJsonObject) throws NoSuchUserException {
        JSONObject tokenJson = new JSONObject(tokenJsonObject);

        String token = tokenJson.getString("token");
        String username = tokenhelper.tokenDeCrypt(token);
        String permission = getPermission(token);

        JSONObject responseObject = new JSONObject();
        responseObject.put("permission", permission);
        responseObject.put("username", username);

        return responseObject.toString();
    }

    void checkPermission(String token, String... permissionToCheck) throws NoSuchUserException, NoPermissionException {
        String permission = getPermission(token);
        for (String p : permissionToCheck) { //userPermission=employee -> equals("admin", "employee")
            if (permission.equals(p)) {
                return;
            }
        }
        throw new NoPermissionException("No such Permission!");
    }

    private String getPermission(String token) throws NoSuchUserException {
        String username = tokenhelper.tokenDeCrypt(token);
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new NoSuchUserException("Username in token does not exist!"));
        return user.getPermission();
    }

    private String parseToken(String value) {
        return tokenhelper.tokenDeCrypt(value);
    }

    public String getUsernameFromToken() {
        String username = "";
        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("token")) {
                    username = parseToken(c.getValue());
                }
            }
        }
        if (VaadinSession.getCurrent().getAttribute("token") != null) {
            username = parseToken(VaadinSession.getCurrent().getAttribute("token").toString());
        }
        return username;
    }
}
