package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data // getter, setter, toString
@Table(name="board_tb")
@Entity
public class Board {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int id;
    private String title;
    private String content;
    private int userId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
