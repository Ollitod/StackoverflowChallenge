/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.db;

import com.gepardec.stackoverflowchallenge.model.Challenge;
import com.gepardec.stackoverflowchallenge.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author praktikant_ankermann
 */
@Stateless
public class DAO implements DAOLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean createUser(User User) {
        em.persist(User);
        return true;
    }

    @Override
    public boolean createChallenge(Challenge challenge) {
        em.persist(challenge);
        return true;
    }

    @Override
    public List<User> readAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<Challenge> readAllChallenges() {
        return em.createQuery("select ch from Challenge ch", Challenge.class).getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        User u;
        if ((u = em.find(User.class, user.getUserId())) == null) {
            return false;
        }
        em.merge(u);
        return true;
    }

    @Override
    public boolean updateChallenge(Challenge challenge) {
        Challenge ch;
        if ((ch = em.find(Challenge.class, challenge.getChallengeId())) == null) {
            return false;
        }
        em.merge(ch);
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        User u;
        if ((u = em.find(User.class, user.getUserId())) == null) {
            return false;
        }
        em.remove(u);
        return true;
    }

    @Override
    public boolean deleteChallenge(Challenge challenge) {
        Challenge ch;
        if ((ch = em.find(Challenge.class, challenge.getChallengeId())) == null) {
            return false;
        }
        em.remove(ch);
        return true;
    }

    @Override
    public User findUser(int userId) {
        return em.find(User.class, userId);
    }

    @Override
    public Challenge findChallenge(int challengeId) {
        return em.find(Challenge.class, challengeId);
    }

    @Override
    public boolean addUserToChallenge(int userId, int challengeId) {
        User User;
        Challenge challenge;
        if ((User = em.find(User.class, userId)) == null) {
            return false;
        }
        if ((challenge = em.find(Challenge.class, challengeId)) == null) {
            return false;
        }
        challenge.addUser(User);
        return true;
    }

}
