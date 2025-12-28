package com.example.Endpoint;


import com.example.entity.Secret;
import com.example.request.SecretRequest;
import com.example.service.SecretService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@Path("/secret")
public class SecretEndpoint {

    @Inject
    SecretService service;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Secret hello(SecretRequest request) {
       return service.buildSecret(request.value);
    }

    @GET
    @Path("getdata")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Secret> getSecret(){
        return service.getData();
    }

    @GET
    @Path("/{token}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSecretMsg(@PathParam("token") String token){
        return service.buildMessageFromLink(token);
    }
}
