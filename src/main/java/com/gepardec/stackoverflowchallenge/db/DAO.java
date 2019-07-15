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
    public boolean createUser(User user) {
        if (findUser(user.getProfileId()) == null) {
            em.persist(user);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean createChallenge(Challenge challenge) {
        em.persist(challenge);
        return true;
    }

    @Override
    public List<User> readAllUsers() {
        return em.createQuery("select u from User u order by u.profileId", User.class).getResultList();
    }

    @Override
    public List<Challenge> readAllChallenges() {
        return em.createQuery("select ch from Challenge ch", Challenge.class).getResultList();
    }

    @Override
    public boolean updateUser(User user) {
        User u;
        if ((u = em.find(User.class, user.getProfileId())) == null) {
            return false;
        }
        em.merge(u);
        return true;
    }

    @Override
    public boolean updateChallenge(Challenge challenge) {
        Challenge ch;
        if ((ch = em.find(Challenge.class, challenge.getId())) == null) {
            return false;
        }
        em.merge(ch);
        return true;
    }

    @Override
    public User deleteUser(long id) {
        User user;
        if ((user = em.find(User.class, id)) == null) {
            return null;
        } else {
            em.remove(user);
            return user;
        }
    }

    @Override
    public Challenge deleteChallenge(long id) {
        Challenge challenge;
        if ((challenge = em.find(Challenge.class, id)) == null) {
            return null;
        } else {
            em.remove(challenge);
            return challenge;
        }
    }

    @Override
    public User findUser(long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public Challenge findChallenge(long challengeId) {
        return em.find(Challenge.class, challengeId);
    }

    @Override
    public boolean addUserToChallenge(long userId, int challengeId) {
        User user;
        Challenge challenge;
        if ((user = em.find(User.class, userId)) == null) {
            return false;
        }
        if ((challenge = em.find(Challenge.class, challengeId)) == null) {
            return false;
        }
        challenge.addParticipant(user);
        return true;
    }
}
