package com.locationcast.security.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 *  <!-- Rest authentication entry point configuration -->
    <http use-expressions="true" entry-point-ref="restAuthenticationEntryPoint">
        <intercept-url pattern="/api/**" />
        <sec:form-login authentication-success-handler-ref="mySuccessHandler"
            authentication-failure-handler-ref="myFailureHandler" />
 
        <logout />
    </http>
 
    <!-- Connect the custom authentication success handler -->
    <beans:bean id="mySuccessHandler"
        class="spring.security.rest.api.security.RestAuthenticationSuccessHandler" />
    <!-- Using default failure handler -->
    <beans:bean id="myFailureHandler"
        class="org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler" />
 
 * @author Khoa
 *
 */
//@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
 
    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
            AuthenticationException arg2) throws IOException, ServletException {
        arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
 
    }
 
}

