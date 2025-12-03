package com.techlab.demo.dto;

public class CreateUserRequest {
    private String username;
    private String password;
    private Integer age;
    private Integer capacity; // ✅ mejor como número
    private String role; // ADMIN o USER

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}