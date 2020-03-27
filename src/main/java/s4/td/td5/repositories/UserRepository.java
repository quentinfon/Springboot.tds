package s4.td.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.td.td5.models.Category;
import s4.td.td5.models.Language;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByLogin(String name);

    User findById(int id);

    List<User> findByScripts(Script s);

    List<User> findAll();

    @Query("select u from User u where upper(u.login) like %:search%")
    List<User> recherche(@Param("search")String recherche);

    @Query("select u from User u where u.login = :login and u.password = :mdp")
    User connexion(@Param("login")String login, @Param("mdp")String mdp);

}