/*
 * Copyright (C) 2008 Ian Dees.
 */

package com.mapki.visuant.parse.targets;


/**
 * @author Ian Dees
 *
 */
public class ParseAntTargetDependencies {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String antBuildFileName = args[0];
        
        // Create the model to store the data in to
        AntBuildfileModel model = new AntBuildfileModel();
        
        // Parse the ant file(s) into the model
        AntFileParser antFileParser = new AntFileParser(model);
        antFileParser.parseFile(antBuildFileName);
        // After reading everything, settle out the dependencies
        model.settleDependencies();
        
        // Write out the graph
        AntFileOutput out = new DotFileOutput(model, "file.dot");
        out.write("out.dot");
    }

}
