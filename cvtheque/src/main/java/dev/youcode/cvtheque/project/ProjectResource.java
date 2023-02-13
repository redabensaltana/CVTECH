package dev.youcode.cvtheque.project;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectResource {

    private final ProjectService projectService;

    public ProjectResource(final ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{proId}")
    public ResponseEntity<List<ProjectDTO>> getProject(@PathVariable final Long proId) {
        return ResponseEntity.ok(projectService.get(proId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProject(@RequestBody @Valid final ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.create(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{proId}")
    public ResponseEntity<Void> updateProject(@PathVariable final Long proId,
            @RequestBody @Valid final ProjectDTO projectDTO) {
        projectService.update(proId, projectDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{proId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProject(@PathVariable final Long proId) {
        projectService.delete(proId);
        return ResponseEntity.noContent().build();
    }

}
