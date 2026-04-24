package com.example.lab7.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Eroare: Filmul cu ID-ul " + id + " nu există în baza de date!");
    }
}