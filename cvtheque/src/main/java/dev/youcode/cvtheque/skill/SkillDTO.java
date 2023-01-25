package dev.youcode.cvtheque.skill;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SkillDTO {

    private Long skillId;

    @NotNull
    @Size(max = 255)
    private String skillType;

    @NotNull
    @Size(max = 255)
    private String skillName;

    @NotNull
    private Long resumeSkillId;

}
