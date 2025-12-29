package com.example.service;

import com.example.entity.Secret;
import com.example.repository.SecretRepository;
import com.example.request.TokenResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.security.SecureRandom;
import java.util.List;

@ApplicationScoped
public class SecretService {

    @Inject
    SecretRepository repository;


    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TOKEN_LENGTH = 12;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            token.append(CHARACTERS.charAt(index));
        }
        return token.toString();
    }

    @Transactional
    public Response buildSecret(String value){
        Secret sc = new Secret(generateToken(),value);
        repository.persist(sc);
        return Response.ok(new TokenResponse(sc.getToken())).build();
    }

    @Transactional
    public String buildMessageFromLink(String token){
      List<Secret> ls =  repository.findAll().list();
      for(int i = 0; i<ls.size(); i++){
          Secret secret = ls.get(i);
          if(secret.getToken().equals(token)){
              if(secret.isUsed()){
                  return "The secret message was one view only";
              }
              secret.markAsUsed();
              return secret.getValue();
          }
      }
      return "not found";
    }

    public List<Secret> getData() {
        return repository.findAll().list();
    }

}
