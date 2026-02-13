package com.estudosjava.curso.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Telephone is mandatory")
    @Pattern(regexp = "\\d{10,11}", message = "Telephone must have 10 or 11 digits (ex: 48999999999)")
    private String phone;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 20, message = "The password must be between 6 and 20 characters")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
