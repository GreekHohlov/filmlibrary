package com.sber.filmlibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class FilmlibraryApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(FilmlibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws SQLException {

    }
}
