package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {

    @Query("select max(s.score)" + " from Submission s" + " join s.id.challenge c" + " where c.id = ?1")
    BigDecimal findHigherScoreByChallengeId(Long challengeId);

    @Query("from Submission s" + " join s.id.challenge c" + " join c.accelerations a"
            + " on a.challenge = c " + " where c.id = ?1 and a.id = ?2")
    List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId);

}
