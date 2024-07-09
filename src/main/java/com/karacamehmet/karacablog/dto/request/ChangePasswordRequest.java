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
public class ChangePasswordRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 6, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String currentPassword;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 6, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String newPassword;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 6, max = 20, message = Messages.ValidationErrors.LENGTH)
    private String confirmNewPassword;
}
