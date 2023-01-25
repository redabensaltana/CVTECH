package dev.youcode.cvtheque.experience;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExperienceDTO {

    private Long expId;

    @NotNull
    @Size(max = 255)
    private String expName;

    @NotNull
    @Size(max = 255)
    private String position;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @Size(max = 255)
    private String tech;

    @NotNull
    private Long resumeExperienceId;

}
