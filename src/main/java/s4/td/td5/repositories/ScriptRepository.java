package s4.td.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.td.td5.models.Category;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;


import java.util.List;

public interface ScriptRepository extends JpaRepository<Script, Integer> {

    List<Script> findByTitle(String title);

    Script findById(int id);

    List<Script> findByOwner(User user);

    List<Script> findByCategory(Category category);

    @Query("select s from Script s where upper(s.title) like %:search% or upper(s.description) like %:search% or upper(s.content) like %:search%")
    List<Script> recherche(@Param("search")String recherche);

}