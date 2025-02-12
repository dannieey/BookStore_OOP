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
    }
}