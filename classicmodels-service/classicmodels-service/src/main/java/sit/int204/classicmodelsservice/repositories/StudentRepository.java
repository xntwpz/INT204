package sit.int204.classicmodelsservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.classicmodelsservice.models.Student;

public interface StudentRepository extends JpaRepository<Student , Integer> {
}
