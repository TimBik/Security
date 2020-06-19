package ru.spring.files.repositories;

import java.util.Optional;

public interface CrudRepositoriesUser<T, L> {
    Optional<T> getUserByLogin(L email);
    Optional<T> findByConfirmCode(L confirmCode);
    void update(L confirm);
}
