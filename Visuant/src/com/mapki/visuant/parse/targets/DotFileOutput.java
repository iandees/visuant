/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.targets;

import com.mapki.visuant.parse.model.AntProject;
import com.mapki.visuant.parse.model.AntTarget;

import java.io.PrintStream;
import java.util.Set;

/**
 * @author Ian Dees
 *
 */
public class DotFileOutput implements AntFileOutput {

    private AntBuildfileModel myModel;
    private String fName;

    /**
     * @param model
     */
    public DotFileOutput(AntBuildfileModel model, String fileName) {
        myModel = model;
        fName = fileName;
    }

    /**
     * {@inheritJavadoc}
     */
    public void write(String fileName) {
        Set<AntProject> allProjects = myModel.getAllProjects();

        try {
//            PrintStream ps = new PrintStream(new FileOutputStream(fName));
            PrintStream ps = System.out;
            
            // Write out the digraph
            ps.println("digraph \"G\" {");
            
            for (AntProject project : allProjects) {
                Set<AntTarget> targets = project.getAllTargets();
                for (AntTarget target : targets) {
                    
                    Set<AntTarget> dependencies = target.getDependencies();
                    if (dependencies != null) {
                        for (AntTarget dependency : dependencies) {
                            ps.println("  \""+target.getName()+"\" -> \""+dependency.getName()+"\";");
                        }
                    } else {
                        ps.println("  \""+target.getName()+"\";");
                    }
                }
            }
            
            ps.println("}");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
