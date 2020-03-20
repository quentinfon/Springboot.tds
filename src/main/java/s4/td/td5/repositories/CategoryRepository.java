package s4.td.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.td.td5.models.Category;
import s4.td.td5.models.Language;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;


import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByName(String name);

    Category findById(int id);

    List<Category> findByScripts(Script s);

    @Query("select c from Category c where upper(c.name) like %:search%")
    List<Script> recherche(@Param("search")String recherche);

}