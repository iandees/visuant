/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.targets;

import com.mapki.visuant.parse.model.AntProject;
import com.mapki.visuant.parse.model.AntTarget;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Ian Dees
 *
 */
public class AntBuildfileModel {

    private static Set<AntProject> projectList = new HashSet<AntProject>();
    private Queue<AntProject> projectQueue;
    
    /**
     * 
     */
    public AntBuildfileModel() {
        projectQueue = new LinkedList<AntProject>();
    }

    /**
     * @param p
     */
    public void addProject(AntProject p) {
        projectList.add(p);
        projectQueue.add(p);
    }

    /**
     * 
     */
    public void popProject() {
        projectQueue.remove();
    }

    /**
     * 
     */
    public void settleDependencies() {
        for (AntProject proj : projectList) {
            Set<AntTarget> targets = proj.getAllTargets();
            for (AntTarget target : targets) {
                target.settleDependencies();
            }
        }
    }

    /**
     * @return
     */
    public Set<AntProject> getAllProjects() {
        return projectList;
    }

    /**
     * @param depString
     * @return
     */
    public static AntTarget targetFromName(String depString) {
        if(depString == null) {
            return null;
        }
        
        for (AntProject proj : projectList) {
            Set<AntTarget> targets = proj.getAllTargets();
            for (AntTarget target : targets) {
                if(target.getName().equals(depString)) {
                    return target;
                }
            }
        }
        
        return null;
    }

}
