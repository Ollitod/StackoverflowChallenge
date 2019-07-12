/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author praktikant_ankermann
 */
@Entity
@Table(name = "challenge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Challenge.findAll", query = "SELECT c FROM Challenge c")
    , @NamedQuery(name = "Challenge.findByChallengeId", query = "SELECT c FROM Challenge c WHERE c.challengeId = :challengeId")
    , @NamedQuery(name = "Challenge.findByChallengeEnd", query = "SELECT c FROM Challenge c WHERE c.challengeEnd = :challengeEnd")
    , @NamedQuery(name = "Challenge.findByChallengeStart", query = "SELECT c FROM Challenge c WHERE c.challengeStart = :challengeStart")
    , @NamedQuery(name = "Challenge.findByChallengeTitle", query = "SELECT c FROM Challenge c WHERE c.challengeTitle = :challengeTitle")})
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "challenge_id")
    private Integer challengeId;
    @Column(name = "challenge_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date challengeEnd;
    @Column(name = "challenge_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date challengeStart;
    @Size(max = 255)
    @Column(name = "challenge_title")
    private String challengeTitle;
    @ManyToMany(mappedBy = "challengeList")
    private List<User> userList;

    public Challenge() {
    }

    public Challenge(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public Date getChallengeEnd() {
        return challengeEnd;
    }

    public void setChallengeEnd(Date challengeEnd) {
        this.challengeEnd = challengeEnd;
    }

    public Date getChallengeStart() {
        return challengeStart;
    }

    public void setChallengeStart(Date challengeStart) {
        this.challengeStart = challengeStart;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (challengeId != null ? challengeId.hashCode() : 0);
        return hash;
    }

    public void addUser(User user) {
        userList.add(user);
        user.getChallengeList().add(this);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Challenge)) {
            return false;
        }
        Challenge other = (Challenge) object;
        if ((this.challengeId == null && other.challengeId != null) || (this.challengeId != null && !this.challengeId.equals(other.challengeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gepardec.stackoverflowchallenge.model.Challenge[ challengeId=" + challengeId + " ]";
    }

}
