package dev.youcode.cvtheque.experience;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


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
    private String startDate;

    private String endDate;

    @Size(max = 255)
    private String tech;

    @NotNull
    private Long resumeExperienceId;

}
