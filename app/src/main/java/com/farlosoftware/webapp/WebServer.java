package com.farlosoftware.webapp;

import static spark.Spark.*;

public class WebServer {
    public static void main(String[] args) {
        // Set the server port
        port(8080);

        // Define routes
        get("/", (req, res) -> {
            res.type("text/plain");
            return "Welcome to the Java Web App!";
        });
        get("/hello", (req, res) -> {
            res.type("text/plain");
            return "Hello, World!";
        });
        post("/echo", (req, res) -> {
            res.type("text/plain");
            String body = req.body(); // Get the POST body
            return "You sent: " + body + "\n";
        });
        get("/greet/:name", (req, res) -> {
            res.type("text/plain");
            String name = req.params(":name"); // Extract URL parameter
            return "Hello, " + name + "!";
        });

        // 404 handler for unmatched routes
        notFound((req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Route not found\"}";
        });


        after((req, res) -> {
                if (res.type() == null || res.type().isEmpty()) {
                    res.type("application/json");
                }
            });
    }
}
