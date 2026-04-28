package com.intern.assignment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "About You cannot be empty")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String about;

    @NotNull(message = "Birthday cannot be empty")
    @Column(nullable = false)
    private LocalDate birthday;

    @NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^(0[0-9]{9}|\\+94[0-9]{9})$", message = "Please enter a valid mobile number (e.g., 0771234567)")
    @Column(nullable = false, length = 15)
    private String mobile;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Country cannot be empty")
    @Column(nullable = false, length = 50)
    private String country;

    // --- Getters and Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}