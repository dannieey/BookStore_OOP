package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
    private int id;
    private String name;
    private String surname;
    private String book;

    public Users(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.book = null;
        saveToDatabase();
    }

    private void saveToDatabase() {
        String query = "INSERT INTO users (name, surname) VALUES (?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }

            System.out.println("\nUser: " + name + " " + surname + " have been successfully added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBook() {
        return book;
    }


    public String getFirstName() {
        return name;
    }
}