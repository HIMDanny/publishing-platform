package com.publishing.article.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ArticlePaginationParameters {
    private Integer page;
    private Integer pageSize;
    private String field;
    private String direction;

    public Integer getPage(){
        return page <= 0 ? 1 : page;
    }

    public Integer getPageSize(){
        return pageSize <= 0 ? 10 : pageSize;
    }

    public String getField(){
        return field == null ? "id" : field;
    }

    public String getDirection(){
        return direction.equalsIgnoreCase("desc") ? "DESC" : "ASC";
    }
}
