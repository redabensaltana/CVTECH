package dev.youcode.cvtheque.language;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/languages", produces = MediaType.APPLICATION_JSON_VALUE)
public class LanguageResource {

    private final LanguageService languageService;

    public LanguageResource(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        return ResponseEntity.ok(languageService.findAll());
    }

    @GetMapping("/{langId}")
    public ResponseEntity<LanguageDTO> getLanguage(@PathVariable final Long langId) {
        return ResponseEntity.ok(languageService.get(langId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createLanguage(@RequestBody @Valid final LanguageDTO languageDTO) {
        return new ResponseEntity<>(languageService.create(languageDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{langId}")
    public ResponseEntity<Void> updateLanguage(@PathVariable final Long langId,
            @RequestBody @Valid final LanguageDTO languageDTO) {
        languageService.update(langId, languageDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{langId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteLanguage(@PathVariable final Long langId) {
        languageService.delete(langId);
        return ResponseEntity.noContent().build();
    }

}
