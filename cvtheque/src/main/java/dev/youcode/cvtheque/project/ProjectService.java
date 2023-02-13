package dev.youcode.cvtheque.project;


import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeRepository resumeRepository;

    public ProjectService(final ProjectRepository projectRepository,
            final ResumeRepository resumeRepository) {
        this.projectRepository = projectRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<ProjectDTO> findAll() {
        final List<Project> projects = projectRepository.findAll(Sort.by("proId"));
        return projects.stream()
                .map((project) -> mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> get(final Long proId) {
        return projectRepository.findProjectsByResumeProjectId(proId)
                .stream().map(project -> mapToDTO(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    public Long create(final ProjectDTO projectDTO) {
        final Project project = new Project();
        mapToEntity(projectDTO, project);
        return projectRepository.save(project).getProId();
    }

    public void update(final Long proId, final ProjectDTO projectDTO) {
        final Project project = projectRepository.findById(proId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(projectDTO, project);
        projectRepository.save(project);
    }

    public void delete(final Long proId) {
        projectRepository.deleteById(proId);
    }

    private ProjectDTO mapToDTO(final Project project, final ProjectDTO projectDTO) {
        projectDTO.setProId(project.getProId());
        projectDTO.setProTitle(project.getProTitle());
        projectDTO.setTechs(project.getTechs());
        projectDTO.setDiscription(project.getDiscription());
        projectDTO.setResumeProjectId(project.getResumeProjectId() == null ? null : project.getResumeProjectId().getResumeId());
        return projectDTO;
    }

    private Project mapToEntity(final ProjectDTO projectDTO, final Project project) {
        project.setProTitle(projectDTO.getProTitle());
        project.setTechs(projectDTO.getTechs());
        project.setDiscription(projectDTO.getDiscription());
        final Resume resumeProjectId = projectDTO.getResumeProjectId() == null ? null : resumeRepository.findById(projectDTO.getResumeProjectId())
                .orElseThrow(() -> new NotFoundException("resumeProjectId not found"));
        project.setResumeProjectId(resumeProjectId);
        return project;
    }

}
