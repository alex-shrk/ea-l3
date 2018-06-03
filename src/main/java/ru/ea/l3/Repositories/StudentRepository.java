package ru.ea.l3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea.l3.Entities.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
