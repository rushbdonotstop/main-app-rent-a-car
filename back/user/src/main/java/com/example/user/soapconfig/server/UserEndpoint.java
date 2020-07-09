package com.example.user.soapconfig.server;

import com.example.user.model.User;
import com.example.user.service.UserService;
import com.example.user.xmlmodel.user.CreateUser;
import com.example.user.xmlmodel.user.DeleteUserById;
import com.example.user.xmlmodel.user.GetUserById;
import com.example.user.xmlmodel.user.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    @Autowired
    UserService userService;

    private static final String NAMESPACE_URI = "http://rentacar.com/user";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUser")
    @ResponsePayload
    public GetUserById createUser(@RequestPayload CreateUser request) {

        System.out.println("Request from app for method create user; Server sent : " + request.getUser().getUsername());

        GetUserById getUserById = new GetUserById();
        User user = userService.createUserFromAgentApp(request.getUser().toModel(request.getUser()));
        getUserById.setUserId(user.getId());

        return getUserById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUser")
    @ResponsePayload
    public GetUserById updateUser(@RequestPayload UpdateUser request) {

        System.out.println("Request from app for method update user; Server sent : " + request.getUser().getUsername());

        GetUserById getUserById = new GetUserById();
        User user = userService.updateUserFromAgentApp(request.getUser().toModel(request.getUser()));
        getUserById.setUserId(user.getId());

        return getUserById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserById")
    @ResponsePayload
    public GetUserById createLocation(@RequestPayload DeleteUserById request) {

        System.out.println("Request from app for method delete user; Server sent : " + request.getUserId());

        GetUserById getUserById = new GetUserById();
        getUserById.setUserId(request.getUserId());

        return getUserById;
    }
}
