package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository // IoC에 뜬다. 내가 new 할 필요 없다.
public class UserRepository {

    private EntityManager em; // 의존성 주입

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        Query query = em.createNativeQuery("insert into user_tb(username, password, email) values (?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }

    @Transactional
    public void saveV2(UserRequest.JoinDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);
    }
}