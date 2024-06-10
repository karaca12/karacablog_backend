package com.karacamehmet.karacablog.dto.request;

import com.karacamehmet.karacablog.core.message.constant.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Email(message = Messages.ValidationErrors.EMAIL)
    private String email;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 3, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String username;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 6, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String password;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 50, message = Messages.ValidationErrors.LENGTH)
    private String firstName;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 50, message = Messages.ValidationErrors.LENGTH)
    private String lastName;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    private LocalDate birthDate;
}
