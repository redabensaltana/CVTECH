package dev.youcode.cvtheque.project;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectDTO {

    private Long proId;

    @NotNull
    @Size(max = 255)
    private String proTitle;

    @NotNull
    @Size(max = 255)
    private String techs;

    @Size(max = 255)
    private String proRepo;

    @NotNull
    private Long resumeProjectId;

}
