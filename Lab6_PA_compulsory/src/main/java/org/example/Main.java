package org.example;
import org.example.GenreDAO;
import org.example.Database;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            var genres = new GenreDAO();

            genres.create("Action12");
            genres.create("Drama12");
            genres.create("Sci-Fi12");

            System.out.println("ID-ul genului 'Action' este: " + genres.findByName("Action12"));
            System.out.println("Genul cu ID-ul 2 este: " + genres.findById(2));

            Database.closeConnection();

        } catch (SQLException e) {
            System.err.println("Eroare la baza de date: " + e.getMessage());
        }
    }
}