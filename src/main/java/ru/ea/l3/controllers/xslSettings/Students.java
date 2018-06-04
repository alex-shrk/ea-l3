package ru.ea.l3.controllers.xslSettings;

import ru.ea.l3.entities.Student;

import javax.sql.rowset.serial.SerialArray;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="students")
@XmlAccessorType(XmlAccessType.FIELD)
public class Students implements Serializable {

   @XmlElement(name="student",type= Student.class)
   private List<Student> students = new ArrayList<>();

    public Students() {
    }

    public Students(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
