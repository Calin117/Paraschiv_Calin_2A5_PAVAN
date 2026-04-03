package org.example.dao;

import org.example.Database;
import org.example.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public void create(String title, java.sql.Date releaseDate, int duration, double score, int genreId) throws SQLException {
        Connection con = Database.getConnection();
        try (con; PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO movies (title, release_date, duration, score, genre_id) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setString(1, title);
            pstmt.setDate(2, releaseDate);
            pstmt.setInt(3, duration);
            pstmt.setDouble(4, score);
            pstmt.setInt(5, genreId);
            pstmt.executeUpdate();
        }
    }

    public List<Movie> findAllFromView() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        Connection con = Database.getConnection();

        try (con; PreparedStatement pstmt = con.prepareStatement("SELECT * FROM movie_report_view");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getString("title"),
                        rs.getDate("release_date"),
                        rs.getDouble("score"),
                        rs.getString("genre_name")
                ));
            }
        }
        return movies;
    }
}