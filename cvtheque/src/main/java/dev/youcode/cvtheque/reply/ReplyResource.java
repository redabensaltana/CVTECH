package dev.youcode.cvtheque.reply;

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
@RequestMapping(value = "/api/replys", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReplyResource {

    private final ReplyService replyService;

    public ReplyResource(final ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> getAllReplys() {
        return ResponseEntity.ok(replyService.findAll());
    }

    @GetMapping("/{replyId}")
    public ResponseEntity<ReplyDTO> getReply(@PathVariable final Long replyId) {
        return ResponseEntity.ok(replyService.get(replyId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createReply(@RequestBody @Valid final ReplyDTO replyDTO) {
        return new ResponseEntity<>(replyService.create(replyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<Void> updateReply(@PathVariable final Long replyId,
            @RequestBody @Valid final ReplyDTO replyDTO) {
        replyService.update(replyId, replyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{replyId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteReply(@PathVariable final Long replyId) {
        replyService.delete(replyId);
        return ResponseEntity.noContent().build();
    }

}
