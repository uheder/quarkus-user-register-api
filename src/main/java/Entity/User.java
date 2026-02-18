package Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name= "users")
@NamedQueries({
        @NamedQuery(
                name = "User.findByEmail",
                query = "SELECT u FROM User WHERE u.email = :email"
        ),
        @NamedQuery(
                name = "User.findActive",
                query = "SELECT u FROM User WHERE u.active = :ativo ORDER BY u.name"
        ),
        @NamedQuery(
                name = "User.findByNameContaining",
                query = "SELECT u FROM User WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :name, '%'))  AND u.active = :active"
        )
}
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min=3, max=50, message="Size should be between 3 and 50")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=8, message = "Password should have at least 8 characters")
    @Column(nullable = false)
    private String password;

    @Column(name = "create_date", nullable = false)
    public LocalDateTime createDate;

    @Column(name = "update_date")
    public  LocalDateTime updateDate;

    @Column(nullable = false)
    public boolean active = true;

    @PrePersist
    public void onCreate() {
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updateDate = LocalDateTime.now();
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public LocalDateTime getCreateDate(){
        return this.createDate;
    }

    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate(){
        return this.updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate){
        this.updateDate = updateDate;
    }

    public boolean isActive(){
        return this.active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
}
