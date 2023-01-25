package dev.youcode.cvtheque.reply;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReplyDTO {

    private Long replyId;

    @NotNull
    private String replyBody;

    @NotNull
    private Long commentReplyId;

}
