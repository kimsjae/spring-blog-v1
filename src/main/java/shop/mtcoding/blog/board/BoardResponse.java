package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

public class BoardResponse {

    @Data
    @AllArgsConstructor
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Timestamp createdAt;
        private Integer userId;
        private String username;

    }

    @Data
    @AllArgsConstructor
    public static class BoardUserId {
        private Integer id;

        public BoardUserId(DetailDTO id) {
        }
    }
}
