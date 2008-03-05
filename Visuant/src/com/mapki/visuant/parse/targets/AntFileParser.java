/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.targets;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Ian Dees
 *
 */
public class AntFileParser {

    private AntBuildfileModel m;

    /**
     * @param model
     */
    public AntFileParser(AntBuildfileModel model) {
        m = model;
    }

    /**
     * @param antBuildFileName
     */
    public void parseFile(String antBuildFileName) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(antBuildFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SAXHandler handler = new SAXHandler(m);
            parser.parse(new InputSource(fis), handler);
        } catch(SAXException e) {
            e.printStackTrace();
        } catch(ParserConfigurationException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
