/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.db;

import com.gepardec.stackoverflowchallenge.model.Challenge;
import com.gepardec.stackoverflowchallenge.model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author praktikant_ankermann
 */
@Local
public interface DAOLocal {

    boolean createUser(User user);

    boolean createChallenge(Challenge challenge);

    List<User> readAllUsers();

    List<Challenge> readAllChallenges();

    @Deprecated
    boolean updateUser(User User);

    Challenge updateChallenge(Challenge challenge);

    User deleteUser(long id);

    Challenge deleteChallenge(int id);

    User findUser(long userId);

    Challenge findChallenge(int challengeId);

    boolean addUserToChallenge(long userId, int challengeId);
}
