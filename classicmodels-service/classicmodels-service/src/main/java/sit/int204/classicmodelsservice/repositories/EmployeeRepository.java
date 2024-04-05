package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.models.Employee;
import sit.int204.classicmodelsservice.models.Office;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
