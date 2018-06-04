package com.songkadi.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Configuration
@ConditionalOnMissingBean(BaseClientDetails.class)
public class BaseClientDetailsConfiguration {

    private final OAuth2ClientProperties credentials;

    protected BaseClientDetailsConfiguration(OAuth2ClientProperties credentials) {
        this.credentials = credentials;
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.credentials")
    public BaseClientDetails oauth2ClientDetails() {
        BaseClientDetails details = new BaseClientDetails();
        if (this.credentials.getClientId() == null) {
            this.credentials.setClientId(UUID.randomUUID().toString());
        }
        details.setClientId(this.credentials.getClientId());
        details.setClientSecret(this.credentials.getClientSecret());
        details.setAuthorizedGrantTypes(Arrays.asList("authorization_code",
                "password", "client_credentials", "implicit", "refresh_token"));
        details.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        details.setRegisteredRedirectUri(Collections.<String>emptySet());
        return details;
    }
}
