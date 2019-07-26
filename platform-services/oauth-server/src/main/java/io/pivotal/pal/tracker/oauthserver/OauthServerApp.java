package io.pivotal.pal.tracker.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

@SpringBootApplication
public class OauthServerApp extends AuthorizationServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(OauthServerApp.class, args);
    }
}