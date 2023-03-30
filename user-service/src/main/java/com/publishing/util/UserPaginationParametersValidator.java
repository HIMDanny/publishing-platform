package com.publishing.util;

import com.google.common.collect.Lists;
import com.publishing.clients.ICheckPaginationParameters;
import com.publishing.clients.article.dto.ArticlePageResponseDto;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;

@Component
public class UserPaginationParametersValidator implements ICheckPaginationParameters {

    enum UserFields {
        ID(Lists.newArrayList("id", "ID")),
        FIRST_NAME(Lists.newArrayList("first_name", "FIRST_NAME", "firstname", "FIRSTNAME",
                "NAME")),
        LAST_NAME(Lists.newArrayList("last_name", "LAST_NAME", "lastname", "LASTNAME")),

        EMAIL(Lists.newArrayList("email", "EMAIL", "e-mail", "E-MAIL", "main", "MAIN"));

        private final List<String> fieldValues;


        UserFields(List<String> fieldValues) {
            this.fieldValues = fieldValues;
        }

        public List<String> getFieldValues() {
            return fieldValues;
        }
    }

    @Override
    public boolean isCorrect(String field) {
        EnumSet<UserFields> userFieldsSet = EnumSet.allOf(UserFields.class);
        boolean isCorrect = userFieldsSet.contains(UserFields.valueOf(field.toUpperCase()));
        if(!isCorrect){
            for (UserFields userFields : userFieldsSet) {
                boolean isContains = userFields.getFieldValues().contains(field);
                if (isContains) return true;
            }
        }
        return isCorrect;
    }
}
