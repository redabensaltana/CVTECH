package dev.youcode.cvtheque.education;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/educations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EducationResource {

    private final EducationService educationService;

    public EducationResource(final EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping
    public ResponseEntity<List<EducationDTO>> getAllEducations() {
        return ResponseEntity.ok(educationService.findAll());
    }

    @GetMapping("/{educId}")
    public ResponseEntity<List<EducationDTO>> getEducation(@PathVariable final Long educId) {
        return ResponseEntity.ok(educationService.findEducationByResumeEducationId(educId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createEducation(
            @RequestBody @Valid final EducationDTO educationDTO) {
        System.out.println(educationDTO.getEducId()  + " " + educationDTO.getEduName());
        return new ResponseEntity<>(educationService.create(educationDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{educId}")
    public ResponseEntity<Void> updateEducation(@PathVariable final Long educId,
            @RequestBody @Valid final EducationDTO educationDTO) {
        educationService.update(educId, educationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{educId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEducation(@PathVariable final Long educId) {
        educationService.delete(educId);
        return ResponseEntity.noContent().build();
    }

}
