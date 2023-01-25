package dev.youcode.cvtheque.experience;

import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ResumeRepository resumeRepository;

    public ExperienceService(final ExperienceRepository experienceRepository,
            final ResumeRepository resumeRepository) {
        this.experienceRepository = experienceRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<ExperienceDTO> findAll() {
        final List<Experience> experiences = experienceRepository.findAll(Sort.by("expId"));
        return experiences.stream()
                .map((experience) -> mapToDTO(experience, new ExperienceDTO()))
                .collect(Collectors.toList());
    }

    public ExperienceDTO get(final Long expId) {
        return experienceRepository.findById(expId)
                .map(experience -> mapToDTO(experience, new ExperienceDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final ExperienceDTO experienceDTO) {
        final Experience experience = new Experience();
        mapToEntity(experienceDTO, experience);
        return experienceRepository.save(experience).getExpId();
    }

    public void update(final Long expId, final ExperienceDTO experienceDTO) {
        final Experience experience = experienceRepository.findById(expId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(experienceDTO, experience);
        experienceRepository.save(experience);
    }

    public void delete(final Long expId) {
        experienceRepository.deleteById(expId);
    }

    private ExperienceDTO mapToDTO(final Experience experience, final ExperienceDTO experienceDTO) {
        experienceDTO.setExpId(experience.getExpId());
        experienceDTO.setExpName(experience.getExpName());
        experienceDTO.setPosition(experience.getPosition());
        experienceDTO.setStartDate(experience.getStartDate());
        experienceDTO.setEndDate(experience.getEndDate());
        experienceDTO.setTech(experience.getTech());
        experienceDTO.setResumeExperienceId(experience.getResumeExperienceId() == null ? null : experience.getResumeExperienceId().getResumeId());
        return experienceDTO;
    }

    private Experience mapToEntity(final ExperienceDTO experienceDTO, final Experience experience) {
        experience.setExpName(experienceDTO.getExpName());
        experience.setPosition(experienceDTO.getPosition());
        experience.setStartDate(experienceDTO.getStartDate());
        experience.setEndDate(experienceDTO.getEndDate());
        experience.setTech(experienceDTO.getTech());
        final Resume resumeExperienceId = experienceDTO.getResumeExperienceId() == null ? null : resumeRepository.findById(experienceDTO.getResumeExperienceId())
                .orElseThrow(() -> new NotFoundException("resumeExperienceId not found"));
        experience.setResumeExperienceId(resumeExperienceId);
        return experience;
    }

}
