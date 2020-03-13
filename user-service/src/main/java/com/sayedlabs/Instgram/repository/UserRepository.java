package com.sayedlabs.Instgram.repository;

import com.sayedlabs.Instgram.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String>{

    Optional<User> findByUserName(String userName);

    boolean existsByPhone(String mobileNo);

    boolean existsByEmail(String email);

}
