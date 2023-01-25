package dev.youcode.cvtheque.hobbie;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HobbieDTO {

    private Long hobId;

    @NotNull
    @Size(max = 255)
    private String hobName;

    @NotNull
    private Long resumeHobbieId;

}
