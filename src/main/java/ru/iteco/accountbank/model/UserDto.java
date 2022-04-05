package ru.iteco.accountbank.model;

import lombok.Builder;
import lombok.Data;
import ru.iteco.accountbank.validation.Create;
import ru.iteco.accountbank.validation.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
public class UserDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Integer id;
    @NotBlank
    private String name;
    @Email
    private String email;
}
