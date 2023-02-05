package dev.youcode.cvtheque.experience;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/experiences", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExperienceResource {

    private final ExperienceService experienceService;

    public ExperienceResource(final ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping
    public ResponseEntity<List<ExperienceDTO>> getAllExperiences() {
        return ResponseEntity.ok(experienceService.findAll());
    }

    @GetMapping("/{expId}")
    public ResponseEntity<ExperienceDTO> getExperience(@PathVariable final Long expId) {
        return ResponseEntity.ok(experienceService.get(expId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createExperience(
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        return new ResponseEntity<>(experienceService.create(experienceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{expId}")
    public ResponseEntity<Void> updateExperience(@PathVariable final Long expId,
            @RequestBody @Valid final ExperienceDTO experienceDTO) {
        experienceService.update(expId, experienceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{expId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteExperience(@PathVariable final Long expId) {
        experienceService.delete(expId);
        return ResponseEntity.noContent().build();
    }

}
