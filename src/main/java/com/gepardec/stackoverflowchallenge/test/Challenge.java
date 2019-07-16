/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.test;

import com.gepardec.stackoverflowchallenge.model.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author praktika2019
 */
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String title;
    
    private Date start;
    
    private Date end;
    
    private GenericEntity<List<User>> participantsEntity;
    
    public Challenge() {
        
    }

    public Challenge(String title, Date start, Date end) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public GenericEntity<List<com.gepardec.stackoverflowchallenge.test.User>> getParticipantsEntity() {
        return participantsEntity;
    }

    public void setParticipantsEntity(GenericEntity<List<com.gepardec.stackoverflowchallenge.test.User>> participantsEntity) {
        this.participantsEntity = participantsEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Challenge)) {
            return false;
        }
        Challenge other = (Challenge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gepardec.stackoverflowchallenge.model.Challenge[ id=" + id + " ]";
    }
    
}