package com.silamoney.client.domain;

public interface Pageable<T> {
    public T atPage(Integer page);

    public T withPerPage(Integer page);
}
