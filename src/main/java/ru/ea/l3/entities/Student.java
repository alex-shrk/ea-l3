package ru.ea.l3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String passpostNumber;


    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasspostNumber() {
        return passpostNumber;
    }

    public void setPasspostNumber(String passpostNumber) {
        this.passpostNumber = passpostNumber;
    }



    @Override
    public String toString() {
        return name;
    }
}
