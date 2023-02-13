package dev.youcode.cvtheque.skill;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/skills", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkillResource {

    private final SkillService skillService;

    public SkillResource(final SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return ResponseEntity.ok(skillService.findAll());
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDTO> getSkill(@PathVariable final Long skillId) {
        return ResponseEntity.ok(skillService.get(skillId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createSkill(@RequestBody @Valid final SkillDTO skillDTO) {
        return new ResponseEntity<>(skillService.create(skillDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<Void> updateSkill(@PathVariable final Long skillId,
            @RequestBody @Valid final SkillDTO skillDTO) {
        skillService.update(skillId, skillDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{skillId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSkill(@PathVariable final Long skillId) {
        skillService.delete(skillId);
        return ResponseEntity.noContent().build();
    }

}
