package com.publishing.article.service;

import com.publishing.article.model.Tag;
import com.publishing.article.repo.TagRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepo tagRepo;

    public void saveTags(Integer articleId, String[] tags) {
        for (String tag : tags) {
            tagRepo.save(Tag.builder().articleId(articleId).tag(tag).build());
        }
    }
}
