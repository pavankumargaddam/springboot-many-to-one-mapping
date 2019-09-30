package org.pk.com.controller;


import io.swagger.annotations.ApiParam;
import org.pk.com.domain.Student;
import org.pk.com.service.StudentService;
import org.pk.com.util.HeaderUtil;
import org.pk.com.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(Student.class);

    private final StudentService studentService;

    private static final String ENTITY = "student";

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) throws Exception {
        log.debug("REST request to save Student : {}", student);
       /* if (student.getId() != null) {
            throw new Exception("A new Student cannot already have an ID"+ENTITY_NAME+"idexists");
        }*/
        Student result = studentService.save(student);
        return ResponseEntity.created(new URI("/api/student/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/student")
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) throws Exception {
        log.debug("REST request to update Student : {}", student);
        if (student.getId() == null) {
            studentService.save(student);
        }
        Student result;
        try {
            result = studentService.save(student);
        } catch (Exception ex) {
            throw ex;
        }
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, student.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);
        studentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Students");
        Page<Student> page = studentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/student");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        Student result = studentService.findOne(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
