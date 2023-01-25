package dev.youcode.cvtheque.hobbie;

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
@RequestMapping(value = "/api/hobbies", produces = MediaType.APPLICATION_JSON_VALUE)
public class HobbieResource {

    private final HobbieService hobbieService;

    public HobbieResource(final HobbieService hobbieService) {
        this.hobbieService = hobbieService;
    }

    @GetMapping
    public ResponseEntity<List<HobbieDTO>> getAllHobbies() {
        return ResponseEntity.ok(hobbieService.findAll());
    }

    @GetMapping("/{hobId}")
    public ResponseEntity<HobbieDTO> getHobbie(@PathVariable final Long hobId) {
        return ResponseEntity.ok(hobbieService.get(hobId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createHobbie(@RequestBody @Valid final HobbieDTO hobbieDTO) {
        return new ResponseEntity<>(hobbieService.create(hobbieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{hobId}")
    public ResponseEntity<Void> updateHobbie(@PathVariable final Long hobId,
            @RequestBody @Valid final HobbieDTO hobbieDTO) {
        hobbieService.update(hobId, hobbieDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hobId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteHobbie(@PathVariable final Long hobId) {
        hobbieService.delete(hobId);
        return ResponseEntity.noContent().build();
    }

}
