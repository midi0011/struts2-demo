package org.example.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.struts2.ServletActionContext;
import org.example.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SessionInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        HttpServletRequest request = ServletActionContext.getRequest();
        String accessToken = (String) session.get("accessToken");

        if (accessToken != null){
            HttpServletResponse responseHeader = ServletActionContext.getResponse();
            responseHeader.setHeader("Authorization", "Bearer " + accessToken);
        }

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            accessToken = authorizationHeader.substring(7); // Extract the accessToken
        }

        // Print session contents for debugging
        System.out.println("Session Contents:");
        for (Map.Entry<String, Object> entry : session.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Token from header: " + accessToken);

        if (session.get("user") == null || accessToken == null) {
            return "login";
        }

        try {
            Claims claims = JWTUtil.validateToken(accessToken);
            if(JWTUtil.isTokenExpired(claims.getExpiration())){
                String refreshToken = (String) session.get("refreshToken");
                if (refreshToken != null && !JWTUtil.isTokenExpired(JWTUtil.validateToken(refreshToken).getExpiration())) {

                    String newAccessToken = JWTUtil.generateAccessToken(claims.getSubject());
                    session.put("accessToken", newAccessToken);
                } else {
                    return "login";
                }
            }
        } catch (ExpiredJwtException e){
            System.out.println(e.getMessage());
            return "login";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "login";
        }

        return invocation.invoke();
    }

}
