package org.example;

import org.example.dao.GenreDAO;
import org.example.dao.MovieDAO;
import org.example.model.Movie;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            var genres = new GenreDAO();
            var movies = new MovieDAO();
            List<Movie> movieList = movies.findAllFromView();

            ReportGenerator report = new ReportGenerator();
            report.generateReport(movieList);

            System.out.println("Raport generat ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Database.closePool();
        }
    }
}