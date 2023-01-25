package dev.youcode.cvtheque.education;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EducationDTO {

    private Long educId;

    @NotNull
    @Size(max = 255)
    private String eduName;

    @NotNull
    private LocalDate startDate;

    private LocalDate andDate;

    @NotNull
    private Long resumeEducationId;

}
