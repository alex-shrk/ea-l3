package ru.ea.l3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.ea.l3.entities.Student;
import ru.ea.l3.repositories.StudentRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentResource {
    @Autowired
    private StudentRepository studentRepository;

    //for UI
    @RequestMapping("/showStudents")
    public String getAllStudents(Model model){
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students",students);
        return "showSt"; //todo разобраться почему не работает, хотя модель заполняется
    }

    @GetMapping("/api/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/api/students/{id}")
    public Student getStudent(@PathVariable long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new Exception("id-" + id);

        return student.get();
    }

    @DeleteMapping("/api/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("/api/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/api/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}
