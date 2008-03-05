/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ian Dees
 *
 */
public class AntProject {

    private String name;
    private String defaultTarget;
    private Set<AntTarget> targets;

    /**
     * @param projectName
     * @param defaultTarget
     */
    public AntProject(String projectName, String theDefaultTarget) {
        name = projectName;
        defaultTarget = theDefaultTarget;
        targets = new HashSet<AntTarget>();
    }

    /**
     * @param target
     */
    public void addTarget(AntTarget target) {
        targets.add(target);
    }

    /**
     * 
     */
    public Set<AntTarget> getAllTargets() {
        return targets;
    }

    public String toString() {
        return name;
    }
    
}
