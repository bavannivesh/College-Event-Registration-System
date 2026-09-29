package com.example.College.Event.Registration.System;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
                <html>
                <head>
                    <title>College Event Registration System</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            text-align: center;
                            margin-top: 100px;
                            background-color: #f4f6f8;
                        }
                        h1 {
                            color: #1f4e79;
                        }
                        p {
                            font-size: 18px;
                        }
                        a {
                            display: inline-block;
                            margin: 10px;
                            padding: 12px 20px;
                            background-color: #1f4e79;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                    </style>
                </head>
                <body>

                    <h1>College Event Registration System</h1>
                    <p>Spring Boot Application is Running Successfully!</p>

                    <a href="/api/students">Students API</a>
                    <a href="/api/events">Events API</a>
                    <a href="/api/organizers">Organizers API</a>
                    <a href="/api/registrations">Registrations API</a>

                </body>
                </html>
                """;
    }
}