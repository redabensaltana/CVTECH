package dev.youcode.cvtheque.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p from Project p where p.resumeProjectId.resumeId = ?1")
    List<Project> findProjectsByResumeProjectId(Long id);
}
