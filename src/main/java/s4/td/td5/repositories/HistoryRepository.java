package s4.td.td5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import s4.td.td5.models.*;


import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findByDate(Date date);

    History findById(int id);

    List<History> findByVersion(Script s);

}