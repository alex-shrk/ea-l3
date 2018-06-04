package ru.ea.l3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.w3c.dom.Document;
import ru.ea.l3.entities.Student;
import ru.ea.l3.repositories.StudentRepository;

import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentResource {

    @Autowired
    private StudentRepository studentRepository;

    /*//for UI
    @RequestMapping("/viewXSLT")
    public ModelAndView getAllStudentsXSLT(HttpServletRequest request,
                                           HttpServletResponse response){
        // builds absolute path of the XML file
        String xmlFile = "resources/test.xml";
        String contextPath = request.getServletContext().getRealPath("");
        String xmlFilePath = contextPath + File.separator + xmlFile;

        Source source = new StreamSource(new File(xmlFilePath));

        // adds the XML source file to the model so the XsltView can detect
        ModelAndView model = new ModelAndView("XSLTView");
        model.addObject("xmlSource", source);

        return model;
    }*/
   /* //for UI
    @RequestMapping("/showStudents")
    public String getAllStudentsXLT(Model model) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.newDocument();
        doc.appendChild(doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"history.xsl\""));
        List<Students> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "showSt"; //todo разобраться почему не работает, хотя модель заполняется
    }
*/
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Student getStudent(@PathVariable long id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new Exception("id-" + id);

        return student.get();
    }

    @DeleteMapping(value ="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value ="/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}
