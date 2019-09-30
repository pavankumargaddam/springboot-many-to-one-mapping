package org.pk.com.service;

import org.pk.com.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Page<Student> findAll(Pageable pageable);

    Student findOne(Long id);

    Student save(Student student);

    void delete(Long id);
}
