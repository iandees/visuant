/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.model;

import com.mapki.visuant.parse.targets.AntBuildfileModel;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Ian Dees
 *
 */
public class AntTarget {

    private String name;
    private String[] dependencyStrings;
    private Set<AntTarget> dependencies;

    /**
     * @param targetName
     * @param dependencies 
     */
    public AntTarget(String targetName, String[] deps) {
        name = targetName;
        dependencyStrings = deps;
        dependencies = new HashSet<AntTarget>();
    }

    public void settleDependencies() {
        if(dependencyStrings == null) {
            return;
        }
        
        for (String depString : dependencyStrings) {
            AntTarget target = AntBuildfileModel.targetFromName(depString);
            if(target != null) {
                dependencies.add(target);
            } else {
                throw new IllegalArgumentException("Could not find a target for depency named " + depString);
            }
        }
    }
    
    /**
     * @return
     */
    public Set<AntTarget> getDependencies() {
        return dependencies;
    }

    public String toString() {
        return name;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }
    
}
