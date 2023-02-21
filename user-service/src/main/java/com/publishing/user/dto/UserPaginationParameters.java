package com.publishing.user.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserPaginationParameters {
    private Integer page;
    private Integer pageSize;
    private String field;
    private String direction;

    public Integer getPage(){
        return (page == null || page <= 0) ? 1 : page;
    }

    public Integer getPageSize(){
        return (pageSize == null || pageSize <= 0) ? 10 : pageSize;
    }

    public String getField(){
        return field == null ? "id" : field;
    }

    public String getDirection(){
        return (direction != null && direction.equalsIgnoreCase("desc")) ? "DESC" : "ASC";
    }
}
