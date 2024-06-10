package com.karacamehmet.karacablog.service.abstraction;

import com.karacamehmet.karacablog.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findOrCreateTagsByNames(List<String> tags);
}
