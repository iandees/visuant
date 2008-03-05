/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.targets;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mapki.visuant.parse.model.AntProject;
import com.mapki.visuant.parse.model.AntTarget;

/**
 * @author Ian Dees
 *
 */
public class SAXHandler extends DefaultHandler {

    private static final String DEPENDS_SEPERATOR = ",";
    private final AntBuildfileModel model;
    private AntProject currentProject;

    /**
     * @param model
     */
    public SAXHandler(AntBuildfileModel theModel) {
        model = theModel;
    }

    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
        if("project".equals(name)) {
            String projectName = attributes.getValue("name");
            String defaultTarget = attributes.getValue("default");
            
            currentProject = new AntProject(projectName, defaultTarget);
            model.addProject(currentProject);
        } else if("import".equals(name)) {
            String fileName = attributes.getValue("file");
            
            AntFileParser p = new AntFileParser(model);
            p.parseFile(fileName);
        } else if("target".equals(name)) {
            String targetName = attributes.getValue("name");
            String dependsString = attributes.getValue("depends");
            String[] depends = null;
            if (dependsString != null) {
                depends = dependsString.split(DEPENDS_SEPERATOR);
            }
            
            AntTarget target = new AntTarget(targetName, depends);
            currentProject.addTarget(target);
        } else if("antcall".equals(name)) {
            
        } else if("ant".equals(name)) {
            
        }
    }

    public void endDocument() throws SAXException {
        model.popProject();
    }
    
}
