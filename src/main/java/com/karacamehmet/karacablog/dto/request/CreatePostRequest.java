package com.karacamehmet.karacablog.dto.request;

import com.karacamehmet.karacablog.core.message.constant.Messages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 100, message = Messages.ValidationErrors.LENGTH)
    private String title;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 10000, message = Messages.ValidationErrors.LENGTH)
    private String content;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String author;
    private List<String> tags;
}
