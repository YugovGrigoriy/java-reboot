package com.example.module11.repo;

import com.example.module11.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User,Long> {



     void deleteUserById(Long id);

    @Modifying
    @Query("update User u set u.name = :name, u.age = :age where u.id = :id")
    void updateUserById(@Param("id") Long id, @Param("name") String name, @Param("age") int age);

}
