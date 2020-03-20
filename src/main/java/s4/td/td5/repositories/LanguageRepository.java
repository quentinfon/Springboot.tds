package s4.td.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.td.td5.models.Category;
import s4.td.td5.models.Language;
import s4.td.td5.models.Script;
import s4.td.td5.models.User;


import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    List<Language> findByName(String name);

    Language findById(int id);

    List<Language> findByScripts(Script s);

    @Query("select l from Language l where upper(l.name) like %:search%")
    List<Language> recherche(@Param("search")String recherche);

}