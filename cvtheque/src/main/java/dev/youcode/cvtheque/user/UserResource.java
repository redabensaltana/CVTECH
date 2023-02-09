package dev.youcode.cvtheque.user;

import dev.youcode.cvtheque.response.Response;
import dev.youcode.cvtheque.resume.ResumeDTO;
import dev.youcode.cvtheque.resume.ResumeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {


    private final UserService userService;
  private final ResumeService resumeService;
    public UserResource(final UserService userService, final ResumeService resumeService) {
        this.userService = userService;
        this.resumeService = resumeService;
    }


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long userId) {
        return ResponseEntity.ok(userService.get(userId));
    }
    @GetMapping("/dataTest")
    @PreAuthorize("hasAuthority('SCOPE_DEV')")
    public Map<String, Object> dataTest(Authentication authentication){
        return Map.of(
                "message","Data test",
                "username",authentication.getName(),
                "authorities",authentication.getAuthorities()
        );
    }

    @PostMapping("/register")
    @ApiResponse(responseCode = "201")
    public Response createUser(@RequestBody @Valid final UserDTO userDTO) {
        if(!userDTO.getUserTitle().equals("CME"))
        {
            userDTO.setRole("DEV");
        }
        else {
            userDTO.setRole("CME");
        }
        Long id  = userService.create(userDTO);
        if(userDTO.getRole().equals("DEV")) {
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setUserResumeId(id);
            resumeService.create(resumeDTO);
        }
        Response response = new Response("User created successfully", 201);
        return response;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable final Long userId,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(userId, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
