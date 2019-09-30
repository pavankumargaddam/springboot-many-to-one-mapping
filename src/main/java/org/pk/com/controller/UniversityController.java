package org.pk.com.controller;

import io.swagger.annotations.ApiParam;
import org.pk.com.domain.University;
import org.pk.com.service.impl.UniversityServiceImpl;
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


@RestController
@RequestMapping("/api")
public class UniversityController {

    private static final String ENTITY_NAME = "University";
    private final UniversityServiceImpl universityService;
    private final Logger log  = LoggerFactory.getLogger(UniversityServiceImpl.class);

    public UniversityController(UniversityServiceImpl universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/university")
    public ResponseEntity<University> createUniversity(@Valid @RequestBody University university) throws Exception {
        log.debug("REST request to save University : {}", university);
        University result = universityService.save(university);
        return ResponseEntity.created(new URI("/api/university/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/university")
    public ResponseEntity<University> updateStudent(@Valid @RequestBody University university) throws Exception {
        log.debug("REST request to update University : {}", university);
        if (university.getId() == null) {
            universityService.save(university);
        }
        University result;
        try {
            result = universityService.save(university);
        } catch (Exception ex) {
            throw ex;
        }
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, university.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/university/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete University : {}", id);
        universityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/university")
    public ResponseEntity<List<University>> getAllStudents(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Students");
        Page<University> page = universityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/university");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/university/{id}")
    public ResponseEntity<University> getStudent(@PathVariable Long id) {
        log.debug("REST request to get University : {}", id);
        University result = universityService.findOne(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
