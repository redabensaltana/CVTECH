package dev.youcode.cvtheque.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query("SELECT s from Skill s where s.resumeSkillId.resumeId = ?1")
    Skill findByResumeSkillId(Long idS);

}
