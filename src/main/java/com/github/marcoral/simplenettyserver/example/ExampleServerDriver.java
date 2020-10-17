package com.github.marcoral.simplenettyserver.example;

import com.github.marcoral.simplenettyserver.example.api.ExampleServer;
import com.github.marcoral.simplenettyserver.example.api.ExampleServerConstants;

public class ExampleServerDriver {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public static ExampleServer launch(String[] args) throws Exception {
        int port = ExampleServerConstants.DEFAULT_PORT;
        if(args.length > 0)
            port = Integer.parseInt(args[0]);

        if(args.length > 1 && args[1].equals("-l")) {
            return ExampleServer.setupLocally(port);
        } else
            return ExampleServer.setupOnPort(port);
    }
}