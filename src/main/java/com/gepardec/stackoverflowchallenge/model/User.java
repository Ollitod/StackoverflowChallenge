/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author praktika2019
 */
@Entity
@Table(name = "so_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_userId")
    private Integer profileId;
    
    @Column(name = "user_username")
    private String username;
    
    @Column(name = "user_link")
    private String link;
    
    @ManyToMany(mappedBy = "participants")
    private List<Challenge> challenges = new ArrayList<>();
    
    public User() {
        
    }
    
    public User(int profileId, String username) {
        this.profileId = profileId;
        this.username = username;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileId != null ? profileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.profileId == null && other.profileId != null) || (this.profileId != null && !this.profileId.equals(other.profileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gepardec.stackoverflowchallenge.model.User[ id=" + profileId + " ]";
    }
    
}
