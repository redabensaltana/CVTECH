package dev.youcode.cvtheque.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentDTO {

    private Long commentId;

    @NotNull
    private String commentBody;

//    @NotNull
    private Long userCommentId;

    @NotNull
    private Long resumeCommentId;

}
