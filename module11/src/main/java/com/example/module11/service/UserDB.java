package com.example.module11.service;

import com.example.module11.entity.User;
import com.example.module11.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;


@Component
public class UserDB {


    UserRepository repository;
    private static int count = 1;

    @Transactional
    public User findByIdUser(Long id) {
        Optional<User> user = repository.findById(id);
        return user.get();
    }

    @Transactional
    public void createUser(int age, String name) {
        try {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            user.setId((long) count);
            count++;
            repository.save(user);

        } catch (Exception e) {
            System.out.println("err to create user: " + e);
        }

    }

    @Transactional
    public void upDateUser(String id,String name,int age){
        try{
            repository.updateUserById(Long.parseLong(id),name,age);
        }catch (Exception e){
            System.out.println("err to update user: " + e);
        }
    }

    @Transactional
    public void delete(String id) {
        repository.deleteUserById(Long.parseLong(id));
    }

    public void inCor(String age, String name) throws RuntimeException {
        if (age.isEmpty() || name.isEmpty() || !StringUtils.isNumeric(age)||StringUtils.isNumeric(name)) {
            throw new RuntimeException("age or name empty");
        }

    }

    public void inCorId(String id) throws RuntimeException {
        if (id.isEmpty() || !StringUtils.isNumeric(id)) {
            throw new RuntimeException("id empty");
        }
    }



    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}

