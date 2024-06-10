package com.karacamehmet.karacablog.dto.request;

import com.karacamehmet.karacablog.core.message.constant.Messages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 3, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String username;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 6, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String password;
}
