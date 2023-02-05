package dev.youcode.cvtheque.education;


import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EducationService {

    private final EducationRepository educationRepository;
    private final ResumeRepository resumeRepository;

    public EducationService(final EducationRepository educationRepository,
            final ResumeRepository resumeRepository) {
        this.educationRepository = educationRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<EducationDTO> findAll() {
        final List<Education> educations = educationRepository.findAll(Sort.by("educId"));
        return educations.stream()
                .map((education) -> mapToDTO(education, new EducationDTO()))
                .collect(Collectors.toList());
    }

    public EducationDTO get(final Long educId) {
        return educationRepository.findById(educId)
                .map(education -> mapToDTO(education, new EducationDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final EducationDTO educationDTO) {
        final Education education = new Education();
        mapToEntity(educationDTO, education);
        return educationRepository.save(education).getEducId();
    }

    public void update(final Long educId, final EducationDTO educationDTO) {
        final Education education = educationRepository.findById(educId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(educationDTO, education);
        educationRepository.save(education);
    }

    public void delete(final Long educId) {
        educationRepository.deleteById(educId);
    }

    private EducationDTO mapToDTO(final Education education, final EducationDTO educationDTO) {
        educationDTO.setEducId(education.getEducId());
        educationDTO.setEduName(education.getEduName());
        educationDTO.setStartDate(education.getStartDate());
        educationDTO.setAndDate(education.getAndDate());
        educationDTO.setResumeEducationId(education.getResumeEducationId() == null ? null : education.getResumeEducationId().getResumeId());
        return educationDTO;
    }

    private Education mapToEntity(final EducationDTO educationDTO, final Education education) {
        education.setEduName(educationDTO.getEduName());
        education.setStartDate(educationDTO.getStartDate());
        education.setAndDate(educationDTO.getAndDate());
        final Resume resumeEducationId = educationDTO.getResumeEducationId() == null ? null : resumeRepository.findById(educationDTO.getResumeEducationId())
                .orElseThrow(() -> new NotFoundException("resumeEducationId not found"));
        education.setResumeEducationId(resumeEducationId);
        return education;
    }

}
