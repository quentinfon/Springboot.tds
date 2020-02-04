package s4.spring.td2.repositories;

import s4.spring.td2.models.Groupe;
import s4.spring.td2.models.User;

import java.util.List;

public interface GroupeRepository {

    List<Groupe> findByName(String name);
    List<Groupe> findById(String id);
    List<Groupe> findByAliases(String aliases);
    List<Groupe> findByEmail(String email);

}
