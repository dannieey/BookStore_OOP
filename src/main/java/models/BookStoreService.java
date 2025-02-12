package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookStoreService {

    public static List<String> getGenres() {

        List<String> genres = new ArrayList<>();
        String query = "SELECT DISTINCT genre FROM books_users";

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                genres.add(rs.getString("genre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }


    public static List<Book> getBooksByGenre(String genre) {
        List<Book> books_users = new ArrayList<>();
        String query = "SELECT * FROM books_users WHERE genre = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, genre);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                books_users.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books_users;
    }
}
