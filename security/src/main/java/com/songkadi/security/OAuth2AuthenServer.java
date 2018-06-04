package com.songkadi.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@ConditionalOnClass(EnableAuthorizationServer.class)
@ConditionalOnMissingBean(AuthorizationServerConfigurer.class)
@ConditionalOnBean(AuthorizationServerEndpointsConfiguration.class)
@EnableConfigurationProperties(AuthorizationServerProperties.class)
@EnableAuthorizationServer // < enable OAuth2 server API
public class OAuth2AuthenServer extends AuthorizationServerConfigurerAdapter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final BaseClientDetails details;

    private final AuthenticationManager authenticationManager;

    private final TokenStore tokenStore;

    private final AccessTokenConverter tokenConverter;

    private final AuthorizationServerProperties properties;

    public OAuth2AuthenServer(BaseClientDetails details,
                              AuthenticationManager authenticationManager,
                              ObjectProvider<TokenStore> tokenStore,
                              ObjectProvider<AccessTokenConverter> tokenConverter,
                              AuthorizationServerProperties properties) {
        this.details = details;
        this.authenticationManager = authenticationManager;
        this.tokenStore = tokenStore.getIfAvailable();
        this.tokenConverter = tokenConverter.getIfAvailable();
        this.properties = properties;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder builder = clients
                .inMemory().withClient(this.details.getClientId());
        builder.secret(this.details.getClientSecret())
                .resourceIds(this.details.getResourceIds().toArray(new String[0]))
                .authorizedGrantTypes(
                        this.details.getAuthorizedGrantTypes().toArray(new String[0]))
                .authorities(
                        AuthorityUtils.authorityListToSet(this.details.getAuthorities())
                                .toArray(new String[0]))
                .scopes(this.details.getScope().toArray(new String[0]));

        if (this.details.getAutoApproveScopes() != null) {
            builder.autoApprove(
                    this.details.getAutoApproveScopes().toArray(new String[0]));
        }
        if (this.details.getAccessTokenValiditySeconds() != null) {
            builder.accessTokenValiditySeconds(
                    this.details.getAccessTokenValiditySeconds());
        }
        if (this.details.getRefreshTokenValiditySeconds() != null) {
            builder.refreshTokenValiditySeconds(
                    this.details.getRefreshTokenValiditySeconds());
        }
        if (this.details.getRegisteredRedirectUri() != null) {
            builder.redirectUris(
                    this.details.getRegisteredRedirectUri().toArray(new String[0]));
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        if (this.tokenConverter != null) {
            endpoints.accessTokenConverter(this.tokenConverter);
        }
        if (this.tokenStore != null) {
            endpoints.tokenStore(this.tokenStore);
        }
        if (this.details.getAuthorizedGrantTypes().contains("password")) {
            endpoints.authenticationManager(this.authenticationManager);
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        if (this.properties.getCheckTokenAccess() != null) {
            security.checkTokenAccess(this.properties.getCheckTokenAccess());
        }
        if (this.properties.getTokenKeyAccess() != null) {
            security.tokenKeyAccess(this.properties.getTokenKeyAccess());
        }
        if (this.properties.getRealm() != null) {
            security.realm(this.properties.getRealm());
        }
    }
}
