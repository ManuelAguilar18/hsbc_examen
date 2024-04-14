package com.hsbc;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;

import static io.muserver.rest.CORSConfigBuilder.corsConfig;

import com.hsbc.controller.ReservaController;

public class ExamenApp {

    public static void main(String[] args) {
        
        MuServer server = MuServerBuilder.httpServer().withHttpPort(8080)
                .addHandler(createRestHandler())
                .start();

        System.out.println("API example: " + server.uri().resolve("/restaurante"));

    }
    
    public static RestHandlerBuilder createRestHandler() {
        return RestHandlerBuilder.restHandler(new ReservaController())
            .withCORS(corsConfig().withAllowedOriginRegex(".*"));
    }
}
