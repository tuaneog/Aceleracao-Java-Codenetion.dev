package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    List<Acceleration> findByCandidatesIdCompanyId(Long companyId);
}
