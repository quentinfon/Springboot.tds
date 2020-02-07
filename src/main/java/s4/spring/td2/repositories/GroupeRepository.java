package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.models.Groupe;
import s4.spring.td2.models.Organization;
import s4.spring.td2.models.User;

import java.util.List;

public interface GroupeRepository  extends JpaRepository<Groupe, Integer> {

    List<Groupe> findByName(String name);
    Groupe findById(int id);
    List<Groupe> findByAliases(String aliases);
    List<Groupe> findByEmail(String email);

}
