package dev.youcode.cvtheque.experience;

import dev.youcode.cvtheque.education.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Query("SELECT e from Experience e where e.resumeExperienceId.resumeId = ?1 ")
    List<Experience> findExperienceByResumeExperienceId(Long id);

}
