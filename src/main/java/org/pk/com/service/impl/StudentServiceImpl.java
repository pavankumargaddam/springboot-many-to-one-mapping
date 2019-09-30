package org.pk.com.service.impl;

import org.pk.com.domain.Student;
import org.pk.com.repositories.StudentRepository;
import org.pk.com.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("studentservice")
public class StudentServiceImpl implements StudentService {

    private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        LOG.debug("findAll Method started");
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student findOne(Long id) {
        LOG.debug("findOne Method started : {}", id);
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student student) {
        LOG.debug("save Method started : {}", student);
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("delete Method started : {}", id);
        studentRepository.deleteById(id);
    }
}
