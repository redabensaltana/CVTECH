package dev.youcode.cvtheque.education;

import dev.youcode.cvtheque.resume.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("SELECT e from Education e where e.resumeEducationId.resumeId = ?1 ")
    List<Education> findEducationByResumeEducationId(Long id);
}
