package com.songkadi.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ClientDetailsLogger {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final OAuth2ClientProperties credentials;

    protected ClientDetailsLogger(OAuth2ClientProperties credentials) {
        this.credentials = credentials;
    }

    @PostConstruct
    public void init() {
        String prefix = "security.oauth2.client";
        boolean defaultSecret = this.credentials.isDefaultSecret();
        logger.info(String.format(
                "Initialized OAuth2 Client%n%n%s.client-id = %s%n"
                        + "%s.client-secret = %s%n%n",
                prefix, this.credentials.getClientId(), prefix,
                defaultSecret ? this.credentials.getClientSecret() : "****"));
    }
}
