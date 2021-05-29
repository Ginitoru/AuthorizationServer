package com.iordache.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private final AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance())
                .tokenKeyAccess("isAuthenticated()");                                 //ca sa obtinem cheia publica trebuie sa ne autentificam cu clientul
        super.configure(security);
    }

  //  http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&scope=read
  //  http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code=.....codul pt a obtine token-ul
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client1")
                .secret("secret1")
                .scopes("read")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .redirectUris("http://localhost:9090");                              //nu exista -> folosit ca sa luat codul de autorizare
    }


    @Bean
    public TokenStore tokenStore(){
        var tokenStore = new JwtTokenStore(converter());                               //acum folosim JWT
        return tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter converter(){
       var converter = new JwtAccessTokenConverter();

       ClassPathResource getKey = new ClassPathResource("key_store.jks");                //din fisierula asta luam public key
       char [] password = "gini123".toCharArray();                                       // parola la fisierul de mai sus

        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(getKey, password);   //bagam fisierul si parola

        converter.setKeyPair(storeKeyFactory.getKeyPair("gini"));                   // gini -> alias-ul cand am creat fisierul ce contine cheile

        return converter;

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(converter());
    }
}
