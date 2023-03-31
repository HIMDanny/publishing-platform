package com.publishing.util;

import com.google.common.collect.Lists;
import com.publishing.clients.ICheckPaginationParameters;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;

@Component
public class ArticlePaginationParametersValidator implements ICheckPaginationParameters {

    enum ArticleFields {

        ID(Lists.newArrayList("id", "ID")),
        TITLE(Lists.newArrayList("title", "TITLE", "name")),
        PUBLISHING_DATE(Lists.newArrayList("publishing_date", "date", "publishingDate",
                "PUBLISHING_DATE", "PUBLISHINGDATE", "DATE")),
        NUMBER_OF_VIEWS(Lists.newArrayList("number_of_views", "number_of_view", "views", "view", "numberOfViews",
                "NUMBER_OF_VIEWS", "NUMBER_OF_VIEW", "NUMBEROFVIEWS", "VIEWS", "VIEWS")),
        NUMBER_OF_LIKES(Lists.newArrayList("number_of_likes", "number_of_like", "likes", "like", "numberOfLikes",
                "NUMBER_OF_LIKES", "NUMBER_OF_LIKE", "NUMBEROFLIKES", "LIKES", "LIKE"));

        private final List<String> fieldValues;


        ArticleFields(List<String> fieldValues) {
            this.fieldValues = fieldValues;
        }

        public List<String> getFieldValues() {
            return fieldValues;
        }
    }

    @Override
    public String getCorrectValue(String field) {
        EnumSet<ArticleFields> articleFieldsSet = EnumSet.allOf(ArticleFields.class);
        for (ArticleFields articleFields : articleFieldsSet) {
            List<String> fieldValues = articleFields.getFieldValues();
            for (String value : fieldValues) {
                if (value.equals(field.toUpperCase())) {
                    String s = articleFields.name().toLowerCase();
                    System.out.println(s);
                    return s;
                }
            }
        }
        return "id";
    }
}
