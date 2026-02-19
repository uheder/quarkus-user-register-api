package DTO;

import Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UserDTO {
    public static class UserRequest {
        @NotBlank(message = "Name is required")
        @Size(min= 3, max = 50, message = "Name should have between 3 and 50 characters")
        public String name;

        @NotBlank
        @Size(max = 100, message = "Email should have a maximum of 100 characters")
        @Email
        public String email;

        @NotBlank
        @Size(min=8, message = "Password should have a minimum of 8 characters")
        public String password;

        public String  getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) {this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class UserResponse {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime creationDate;
        private boolean active;

        public static UserResponse fromEntity(User user){
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setName(user.getName());
            response.setEmail(user.getEmail());
            response.setCreationDate(user.getCreateDate());
            response.setActive(user.isActive());
            return response;
        }

        // getters and setters

        public Long  getId() {  return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public LocalDateTime getCreateDate() { return creationDate; }
        public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

        public boolean isActive() { return active; }
        public void setActive(boolean active) { this.active = active; }
    }

    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        public String email;

        @NotBlank(message = "Password is required")
        public String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String token;
        private UserResponse user;

        public LoginResponse(String token, User user) {
            this.token = token;
            this.user = UserResponse.fromEntity(user);
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }

        public UserResponse getUser() { return user; }
        public void setUser(UserResponse user) { this.user = user; }
    }
}
