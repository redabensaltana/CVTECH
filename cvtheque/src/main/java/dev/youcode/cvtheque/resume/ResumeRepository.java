package dev.youcode.cvtheque.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r from Resume r where r.userResumeId.userId = ?1 ")
    Resume findResumeByUserResumeId(Long id);
}
