package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.models.Employee;
import sit.int204.classicmodelsservice.models.Office;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office,String> {
    public List<Office> findByCityContains(String city);
}