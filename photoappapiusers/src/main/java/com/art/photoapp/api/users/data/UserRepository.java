package com.art.photoapp.api.users.data;

import org.springframework.data.repository.CrudRepository;

//<data type of object that we need to store in database,type of database  ID>
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

}
