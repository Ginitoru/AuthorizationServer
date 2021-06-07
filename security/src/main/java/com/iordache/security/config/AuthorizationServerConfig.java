package com.iordache.security.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private final AuthenticationManager authenticationManager;
    private final ClientDetailsService clientDetailsService; // in nici un caz nu se injecteaza ClientDetailsService -> StackOverflow -> https://stackoverflow.com/questions/31798631/stackoverflowerror-in-spring-oauth2-with-custom-clientdetailsservice
                                                             //sau mai e varianta in care facem @Primary implementarea interfeteti deoarece spring mai are definit un ClientDetailsService -> stackOverflow

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(new BCryptPasswordEncoder())                   //daca vrem sa encodam cu altceva parola la client aici setam
                .tokenKeyAccess("permitAll()");                                 //ca sa obtinem cheia publica trebuie sa ne autentificam cu clientul
        super.configure(security);
    }

  //  http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&scope=read
  //  http://localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code=.....codul pt a obtine token-ul
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);

}


    @Bean
    public TokenStore tokenStore(){
        var tokenStore = new JwtTokenStore(converter());                               //acum folosim JWT
        return tokenStore;
    }


//    @Bean  //NU MERGE
//    public JwtAccessTokenConverter converter(){
//       var converter = new JwtAccessTokenConverter();
//
//       ClassPathResource getKey = new ClassPathResource("key_store.jks");                //din fisierula asta luam public key
//       char [] password = "gini123".toCharArray();                                       // parola la fisierul de mai sus
//
//        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(getKey, password);   //bagam fisierul si parola
//
//        converter.setKeyPair(storeKeyFactory.getKeyPair("gini"));                   // gini -> alias-ul cand am creat fisierul ce contine cheile
//
//        return converter;
//
//    }

    @Bean
    public JwtAccessTokenConverter converter(){   //ne trebuie un converter pentru JWT
        var conv = new JwtAccessTokenConverter();

        conv.setSigningKey("sdfasdfasfasdfadsfasdfasdfadsfasdfasdfasdfasdfasdf");                //cheia de semnatura
        return conv;

    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(converter());
    }
}
