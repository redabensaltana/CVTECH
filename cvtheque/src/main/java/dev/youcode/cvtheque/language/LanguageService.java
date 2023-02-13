package dev.youcode.cvtheque.language;


import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final ResumeRepository resumeRepository;

    public LanguageService(final LanguageRepository languageRepository,
            final ResumeRepository resumeRepository) {
        this.languageRepository = languageRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<LanguageDTO> findAll() {
        final List<Language> languages = languageRepository.findAll(Sort.by("langId"));
        return languages.stream()
                .map((language) -> mapToDTO(language, new LanguageDTO()))
                .collect(Collectors.toList());
    }

    public LanguageDTO get(final Long langId) {
        return languageRepository.findById(langId)
                .map(language -> mapToDTO(language, new LanguageDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final LanguageDTO languageDTO) {
        final Language language = new Language();
        mapToEntity(languageDTO, language);
        return languageRepository.save(language).getLangId();
    }

    public void update(final Long langId, final LanguageDTO languageDTO) {
        final Language language = languageRepository.findById(langId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(languageDTO, language);
        languageRepository.save(language);
    }

    public void delete(final Long langId) {
        languageRepository.deleteById(langId);
    }

    private LanguageDTO mapToDTO(final Language language, final LanguageDTO languageDTO) {
        languageDTO.setLangId(language.getLangId());
        languageDTO.setLangName(language.getLangName());
        languageDTO.setLangLevel(language.getLangLevel());
        languageDTO.setResumeLanguageId(language.getResumeLanguageId() == null ? null : language.getResumeLanguageId().getResumeId());
        return languageDTO;
    }

    private Language mapToEntity(final LanguageDTO languageDTO, final Language language) {
        language.setLangName(languageDTO.getLangName());
        language.setLangLevel(languageDTO.getLangLevel());
        final Resume resumeLanguageId = languageDTO.getResumeLanguageId() == null ? null : resumeRepository.findById(languageDTO.getResumeLanguageId())
                .orElseThrow(() -> new NotFoundException("resumeLanguageId not found"));
        language.setResumeLanguageId(resumeLanguageId);
        return language;
    }

}
