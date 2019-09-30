package org.pk.com.service.impl;

import org.pk.com.domain.University;
import org.pk.com.repositories.UniversityRepository;
import org.pk.com.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("universityservice")
public class UniversityServiceImpl implements UniversityService {

    private final Logger log = LoggerFactory.getLogger(UniversityServiceImpl.class);

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University save(University university) {
        log.debug("saving university object : {}",university);
        return universityRepository.save(university);
    }

    @Override
    public void delete(Long id) {
        universityRepository.deleteById(id);
    }

    @Override
    public Page<University> findAll(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    @Override
    public University findOne(Long id) {
        return universityRepository.findById(id).orElse(null);
    }
}
