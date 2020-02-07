package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.models.Organization;
import s4.spring.td2.models.User;

import java.util.List;

public interface UserRepository  extends JpaRepository<User, Integer> {
    List<User> findByFirstname(String firstname);
    User findById(int id);
    List<User> findByLastname(String lastname);
    List<User> findByEmail(String email);
    List<User> findBySuspended(String suspended);
}
