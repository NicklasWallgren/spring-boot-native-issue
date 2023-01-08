package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where id = :id", nativeQuery = true)
    UserProjection findByIdCustom(final Long id);

}
