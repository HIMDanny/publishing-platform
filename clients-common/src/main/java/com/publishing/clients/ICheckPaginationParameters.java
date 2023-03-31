package com.publishing.clients;

public interface ICheckPaginationParameters<E extends Enum<?>> {
    E getCorrectValue(String field);
}
