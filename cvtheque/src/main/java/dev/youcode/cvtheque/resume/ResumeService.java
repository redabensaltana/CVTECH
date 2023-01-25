package dev.youcode.cvtheque.resume;

import dev.youcode.cvtheque.user.User;
import dev.youcode.cvtheque.user.UserRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(final ResumeRepository resumeRepository,
            final UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public List<ResumeDTO> findAll() {
        final List<Resume> resumes = resumeRepository.findAll(Sort.by("resumeId"));
        return resumes.stream()
                .map((resume) -> mapToDTO(resume, new ResumeDTO()))
                .collect(Collectors.toList());
    }

    public ResumeDTO get(final Long resumeId) {
        return resumeRepository.findById(resumeId)
                .map(resume -> mapToDTO(resume, new ResumeDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final ResumeDTO resumeDTO) {
        final Resume resume = new Resume();
        mapToEntity(resumeDTO, resume);
        return resumeRepository.save(resume).getResumeId();
    }

    public void update(final Long resumeId, final ResumeDTO resumeDTO) {
        final Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(resumeDTO, resume);
        resumeRepository.save(resume);
    }

    public void delete(final Long resumeId) {
        resumeRepository.deleteById(resumeId);
    }

    private ResumeDTO mapToDTO(final Resume resume, final ResumeDTO resumeDTO) {
        resumeDTO.setResumeId(resume.getResumeId());
        resumeDTO.setUserResumeId(resume.getUserResumeId() == null ? null : resume.getUserResumeId().getUserId());
        return resumeDTO;
    }

    private Resume mapToEntity(final ResumeDTO resumeDTO, final Resume resume) {
        final User userResumeId = resumeDTO.getUserResumeId() == null ? null : userRepository.findById(resumeDTO.getUserResumeId())
                .orElseThrow(() -> new NotFoundException("userResumeId not found"));
        resume.setUserResumeId(userResumeId);
        return resume;
    }

}
