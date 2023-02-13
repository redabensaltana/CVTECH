package dev.youcode.cvtheque.hobbie;


import dev.youcode.cvtheque.resume.Resume;
import dev.youcode.cvtheque.resume.ResumeRepository;
import dev.youcode.cvtheque.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class HobbieService {

    private final HobbieRepository hobbieRepository;
    private final ResumeRepository resumeRepository;

    public HobbieService(final HobbieRepository hobbieRepository,
            final ResumeRepository resumeRepository) {
        this.hobbieRepository = hobbieRepository;
        this.resumeRepository = resumeRepository;
    }

    public List<HobbieDTO> findAll() {
        final List<Hobbie> hobbies = hobbieRepository.findAll(Sort.by("hobId"));
        return hobbies.stream()
                .map((hobbie) -> mapToDTO(hobbie, new HobbieDTO()))
                .collect(Collectors.toList());
    }

    public HobbieDTO get(final Long hobId) {
        return hobbieRepository.findById(hobId)
                .map(hobbie -> mapToDTO(hobbie, new HobbieDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = new Hobbie();
        mapToEntity(hobbieDTO, hobbie);
        return hobbieRepository.save(hobbie).getHobId();
    }

    public void update(final Long hobId, final HobbieDTO hobbieDTO) {
        final Hobbie hobbie = hobbieRepository.findById(hobId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(hobbieDTO, hobbie);
        hobbieRepository.save(hobbie);
    }

    public void delete(final Long hobId) {
        hobbieRepository.deleteById(hobId);
    }

    private HobbieDTO mapToDTO(final Hobbie hobbie, final HobbieDTO hobbieDTO) {
        hobbieDTO.setHobId(hobbie.getHobId());
        hobbieDTO.setHobName(hobbie.getHobName());
        hobbieDTO.setResumeHobbieId(hobbie.getResumeHobbieId() == null ? null : hobbie.getResumeHobbieId().getResumeId());
        return hobbieDTO;
    }

    private Hobbie mapToEntity(final HobbieDTO hobbieDTO, final Hobbie hobbie) {
        hobbie.setHobName(hobbieDTO.getHobName());
        final Resume resumeHobbieId = hobbieDTO.getResumeHobbieId() == null ? null : resumeRepository.findById(hobbieDTO.getResumeHobbieId())
                .orElseThrow(() -> new NotFoundException("resumeHobbieId not found"));
        hobbie.setResumeHobbieId(resumeHobbieId);
        return hobbie;
    }

}
