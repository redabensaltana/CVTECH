package dev.youcode.cvtheque.comment;

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
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentResource {

    private final CommentService commentService;

    public CommentResource(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable final Long commentId) {
        return ResponseEntity.ok(commentService.get(commentId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createComment(@RequestBody @Valid final CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.create(commentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable final Long commentId,
            @RequestBody @Valid final CommentDTO commentDTO) {
        commentService.update(commentId, commentDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteComment(@PathVariable final Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }

}
