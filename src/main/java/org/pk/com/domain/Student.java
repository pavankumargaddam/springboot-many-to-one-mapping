package org.pk.com.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "SECTION")
    private String section;

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "UNIVERSITY_STU_ID")
    private University university;

    public Student() {
    }

    public Student(String firstName, String lastName, String section, University university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.section = section;
        this.university = university;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", section='" + section + '\'' +
                ", university=" + university +
                '}';
    }
}
