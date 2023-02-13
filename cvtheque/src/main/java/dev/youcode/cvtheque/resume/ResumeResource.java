package dev.youcode.cvtheque.resume;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/resumes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResumeResource {

    private final ResumeService resumeService;

    public ResumeResource(final ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping
    public ResponseEntity<List<ResumeDTO>> getAllResumes() {
        return ResponseEntity.ok(resumeService.findAll());
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable final Long resumeId) {
        return ResponseEntity.ok(resumeService.get(resumeId));
    }

    @GetMapping("/{lname}")
    public ResponseEntity<ResumeDTO> getResume(@PathVariable final String lname) {
        return ResponseEntity.ok(resumeService.getByLastname(lname));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createResume(@RequestBody @Valid final ResumeDTO resumeDTO) {
        return new ResponseEntity<>(resumeService.create(resumeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{resumeId}")
    public ResponseEntity<Void> updateResume(@PathVariable final Long resumeId,
            @RequestBody @Valid final ResumeDTO resumeDTO) {
        resumeService.update(resumeId, resumeDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/status/{resumeId}")
    public ResponseEntity<Void> updateResume(@PathVariable final Long resumeId,
                                             @RequestBody @Valid final String body) {
        resumeService.updateStatus(resumeId, body);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{resumeId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteResume(@PathVariable final Long resumeId) {
        resumeService.delete(resumeId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/resume/{userId}")
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable final Long userId) {
        return ResponseEntity.ok(resumeService.getByuserId(userId));
    }
}
