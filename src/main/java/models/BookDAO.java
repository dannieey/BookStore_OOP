package models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(String title, String author, String genre, double price) {


        String sql = "INSERT INTO books_users (title, author, genre, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3,genre);
            statement.setDouble(4, price);
            statement.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        String sql = "SELECT * FROM books_users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                double price = resultSet.getDouble("price");

                System.out.println("ID: " + id + "  |  Title: " + title + "  |  Author: " + author + "  |  Genre: " + genre + "  |  Price: " + price + " tenge.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}