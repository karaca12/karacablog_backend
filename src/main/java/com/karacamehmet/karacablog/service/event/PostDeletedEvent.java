package com.karacamehmet.karacablog.service.event;

import com.karacamehmet.karacablog.model.Comment;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class PostDeletedEvent extends ApplicationEvent {
    private final List<Comment> comments;

    public PostDeletedEvent(Object source, List<Comment> comments) {
        super(source);
        this.comments = comments;
    }
}
