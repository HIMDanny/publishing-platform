package com.publishing.util;

import com.google.common.collect.Lists;
import com.publishing.clients.ICheckPaginationParameters;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;

@Component
public class CategoryPaginationParametersValidator implements ICheckPaginationParameters {

    enum CategoryFields{

        ID(Lists.newArrayList("id", "ID")),
        NAME(Lists.newArrayList("name", "NAME", "title", "TITLE"));

        private final List<String> fieldValues;


        CategoryFields(List<String> fieldValues) {
            this.fieldValues = fieldValues;
        }

        public List<String> getFieldValues() {
            return fieldValues;
        }
    }

    @Override
    public boolean isCorrect(String field) {
        EnumSet<CategoryFields> categoryFieldsSet = EnumSet.allOf(CategoryFields.class);
        boolean isCorrect = categoryFieldsSet.contains(CategoryFields.valueOf(field.toUpperCase()));
        if(!isCorrect){
            for (CategoryFields categoryFields : categoryFieldsSet) {
                boolean isContains = categoryFields.getFieldValues().contains(field);
                if (isContains) return true;
            }
        }
        return isCorrect;
    }
}
