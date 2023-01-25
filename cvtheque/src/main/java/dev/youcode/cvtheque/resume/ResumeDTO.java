package dev.youcode.cvtheque.resume;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResumeDTO {

    private Long resumeId;

    @NotNull
    private Long userResumeId;

}
