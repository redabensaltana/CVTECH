package dev.youcode.cvtheque.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long userId;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;
    @NotNull
    @Size(max = 255)
    private String userTitle;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    @Size(max = 255)
    private String tel;

    @Size(max = 255)
    private String linkden;

    @Size(max = 255)
    private String github;

    @NotNull
    @Size(max = 255)
    private String password;
    @Size(max = 255)
    private String role;

    private Long resumeId;
}
