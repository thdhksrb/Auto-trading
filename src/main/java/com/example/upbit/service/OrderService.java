package com.example.upbit.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.upbit.dto.AccountDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${security.access-key}")
    private String MY_ACCESS_KEY;
    @Value("${security.secret-key}")
    private String MY_SECRET_KEY;

    public List<AccountDTO> executeGetAccounts() {
        System.out.println("executeGetAccounts실행");
        String accessKey = MY_ACCESS_KEY;
        String secretKey = MY_SECRET_KEY;
        String serverUrl = "https://api.upbit.com/v1/accounts";

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl);
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

//            System.out.println(EntityUtils.toString(entity, "UTF-8"));

            String jsonResponse = EntityUtils.toString(entity, "UTF-8");
            List<AccountDTO> accounts = objectMapper.readValue(jsonResponse, new TypeReference<List<AccountDTO>>() {});

            return accounts;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }



}
