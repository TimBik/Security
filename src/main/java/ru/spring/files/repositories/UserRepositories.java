package ru.spring.files.repositories;

import ru.spring.files.model.User;

public interface UserRepositories extends CrudRepositories<User, Long>, CrudRepositoriesUser<User, String> {

}
