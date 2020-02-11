package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.spring.td2.models.Organization;

import java.util.List;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByDomain(String domain);
    Organization findById(int id);
    List<Organization> findByName(String name);

    @Query("select o from Organization o where upper(o.name) like %:search% or upper(o.domain) like %:search% or upper(o.aliases) like %:search%")
    List<Organization> recherche(@Param("search")String recherche);

}