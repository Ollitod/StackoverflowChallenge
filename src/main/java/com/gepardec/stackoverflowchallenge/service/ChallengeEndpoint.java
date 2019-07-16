/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.service;

import com.gepardec.stackoverflowchallenge.db.DAOLocal;
import com.gepardec.stackoverflowchallenge.model.Challenge;
import com.gepardec.stackoverflowchallenge.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.beanutils.BeanUtils;

/**
 * REST Web Service
 *
 * @author praktikant_ankermann
 */
@Path("challenge")
public class ChallengeEndpoint {

    @Context
    private UriInfo context;

    @EJB
    private DAOLocal dao;

    /**
     * Creates a new instance of ChallengeEndpoint
     */
    public ChallengeEndpoint() {
    }

    /**
     * Retrieves representation of an instance of
     * com.gepardec.stackoverflow.service.ChallengeEndpoint
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChallengeById(@PathParam("id") int id) {
        Challenge challenge;
        if ((challenge = dao.findChallenge(id)) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(challenge).build();
        }
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllChallenges() throws Exception{
        List<Challenge> challenges = dao.readAllChallenges();
//        return challenges.get(0).getParticipants().size() + "";
        List<com.gepardec.stackoverflowchallenge.test.Challenge> resultList = new ArrayList<>();
        for(Challenge challenge : challenges){
            com.gepardec.stackoverflowchallenge.test.Challenge resultChallenge = new com.gepardec.stackoverflowchallenge.test.Challenge();
            BeanUtils.copyProperties(resultChallenge, challenge);
            List<com.gepardec.stackoverflowchallenge.test.User> resultParticipants = new ArrayList<>();
            for(User user: challenge.getParticipants()){
                com.gepardec.stackoverflowchallenge.test.User resultUser = new com.gepardec.stackoverflowchallenge.test.User();
                BeanUtils.copyProperties(resultUser, user);
                resultParticipants.add(resultUser);
            }
            
            resultChallenge.setParticipantsEntity(new GenericEntity<List<com.gepardec.stackoverflowchallenge.test.User>>(resultParticipants){});
            resultList.add(resultChallenge);
        }
        return challenges.isEmpty()
                ? Response.status(Response.Status.NO_CONTENT).build()
                : Response.ok(new GenericEntity<List<com.gepardec.stackoverflowchallenge.test.Challenge>>(resultList){}).build();
//return challenges.get(0).getParticipants().size();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createChallenge(Challenge challenge) {
        if (dao.createChallenge(challenge)) {
            return Response.status(Response.Status.CREATED).entity(challenge).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateChallenge(Challenge challenge) {
        challenge = dao.updateChallenge(challenge);
        if (challenge != null) {
            return Response.ok(challenge).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteChallenge(@PathParam("id") Integer id) {
        Challenge challenge = dao.deleteChallenge(id);
        if (challenge != null) {
            return Response.ok(challenge).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("addUser/{chId}/{uId}")
    public String addUserToChallenge(@PathParam("chId") int chId, @PathParam("uId") long uId) {
        return dao.addUserToChallenge(uId, chId) ? "success" : "failure";
    }
}
