package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository // DB와 상호작용하는 데 사용되는 어노테이션. DB와 상호작용하는 클래스임을 명시적으로 표시
public class BoardRepository {

    private final EntityManager em;

    public Board findById(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);

        Board board = (Board) query.getSingleResult(); // getSingleResult는 Object. Board로 다운캐스팅. 리턴타입 Board
        return board;
    }

}
