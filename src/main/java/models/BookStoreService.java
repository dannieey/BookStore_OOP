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
}
