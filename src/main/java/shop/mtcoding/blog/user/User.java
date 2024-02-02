package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="user_tb")
public class User {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int id;

    @Column(unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
