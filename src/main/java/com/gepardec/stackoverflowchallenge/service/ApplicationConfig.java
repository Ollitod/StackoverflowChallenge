/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gepardec.stackoverflowchallenge.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author praktikant_ankermann
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    {
        String certificatesTrustStorePath = "/usr/lib/jvm/jre/lib/security/cacerts";
        System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.gepardec.stackoverflowchallenge.service.ChallengeEndpoint.class);
        resources.add(com.gepardec.stackoverflowchallenge.service.UserEndpoint.class);
    }

}
