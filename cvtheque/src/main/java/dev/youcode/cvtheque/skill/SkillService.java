package dev.youcode.cvtheque.skill;


import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;

    public SkillService(final SkillRepository skillRepository,
            final ResumeRepository resumeRepository) {
        this.skillRepository = skillRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<SkillDTO> findAll() {
        final List<Skill> skills = skillRepository.findAll(Sort.by("skillId"));
        return skills.stream()
                .map((skill) -> mapToDTO(skill, new SkillDTO()))
                .collect(Collectors.toList());
    }

    public SkillDTO get(final Long skillId) {
        return skillRepository.findById(skillId)
                .map(skill -> mapToDTO(skill, new SkillDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final SkillDTO skillDTO) {
        final Skill skill = new Skill();
        mapToEntity(skillDTO, skill);
        return skillRepository.save(skill).getSkillId();
    }

    public void update(final Long skillId, final SkillDTO skillDTO) {
        final Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(skillDTO, skill);
        skillRepository.save(skill);
    }

    public void delete(final Long skillId) {
        skillRepository.deleteById(skillId);
    }

    private SkillDTO mapToDTO(final Skill skill, final SkillDTO skillDTO) {
        skillDTO.setSkillId(skill.getSkillId());
        skillDTO.setSkillType(skill.getSkillType());
        skillDTO.setSkillName(skill.getSkillName());
        skillDTO.setResumeSkillId(skill.getResumeSkillId() == null ? null : skill.getResumeSkillId().getResumeId());
        return skillDTO;
    }

    private Skill mapToEntity(final SkillDTO skillDTO, final Skill skill) {
        skill.setSkillType(skillDTO.getSkillType());
        skill.setSkillName(skillDTO.getSkillName());
        final Resume resumeSkillId = skillDTO.getResumeSkillId() == null ? null : resumeRepository.findById(skillDTO.getResumeSkillId())
                .orElseThrow(() -> new NotFoundException("resumeSkillId not found"));
        skill.setResumeSkillId(resumeSkillId);
        return skill;
    }

}
