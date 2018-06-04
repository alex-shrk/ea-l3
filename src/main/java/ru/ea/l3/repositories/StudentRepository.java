package ru.ea.l3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ea.l3.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

}
