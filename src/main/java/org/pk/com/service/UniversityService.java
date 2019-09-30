package org.pk.com.service;

import org.pk.com.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UniversityService {

    University save(University university);

    void delete(Long id);

    Page<University> findAll(Pageable pageable);

    University findOne(Long id);

}
