package s4.spring.td2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s4.spring.td2.models.Organization;

import java.util.List;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByDomain(String domain);
    Organization findById(int id);
    List<Organization> findByName(String name);

}