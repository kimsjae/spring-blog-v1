package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.time.LocalDateTime;

@Data // getter, setter, toString
@Entity
@Table(name="board_tb")
public class Board {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int id;
    private String title;
    private String content;

    @ManyToOne // Many는 Board, One은 User 1 : N // 포린키 만들어짐
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;
}