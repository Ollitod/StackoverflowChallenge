/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.service;

import com.gepardec.stackoverflowchallenge.db.DAOLocal;
import com.gepardec.stackoverflowchallenge.model.User;
import com.gepardec.stackoverflowchallenge.utils.StackExchangeUtils;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author praktikant_ankermann
 */
@Path("user")
public class UserEndpoint {

    @Context
    private UriInfo context;

    @EJB
    private DAOLocal dao;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") long id) {
        User user;
        if ((user = dao.findUser(id)) == null) {
            String json = StackExchangeUtils.sendRequestAndGetJson("users/" + id, "GET");
            if (json != null) {
                return Response.ok(json).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } else {
            return Response.ok(user).build();
        }
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSoUser(User user) {
        String json = StackExchangeUtils.sendRequestAndGetJson("users/" + user.getProfileId(), "GET");
        if (json == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (dao.createUser(user)) {
                return Response.status(Response.Status.CREATED).entity(user).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        }
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAllUsers() {
        List<User> soUsers = dao.readAllUsers();
        return soUsers.isEmpty()
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(new GenericEntity<List<User>>(soUsers) {
                }).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") Integer id) {
        User user = dao.deleteUser(id);
        if (user != null) {
            return Response.ok(user).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
