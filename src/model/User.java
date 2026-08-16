package model;

public class User {

    private int id;
    private String username;
    private String password;
    private String createdAt;

    // Default Constructor
    public User() {
    }

    // Constructor for Registration
    public User(String username, String password, String createdAt) {
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Constructor with ID
    public User(int id, String username, String password, String createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    // =====================
    // Getters
    // =====================

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // =====================
    // Setters
    // =====================

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username == null ? "" : username.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password.trim();
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {

        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';

    }

}