package com.querydsl.example.config;

import com.querydsl.example.dao.*;
import com.querydsl.example.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TestDataServiceImpl implements TestDataService {


    @Autowired PersonDao personDao;
 
    @Override
    public void addTestData() {
       
        // persons
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setEmail("john.doe@aexample.com");
        personDao.save(person);

        Person person2 = new Person();
        person2.setFirstName("Mary");
        person2.setLastName("Blue");
        person2.setEmail("mary.blue@example.com");
        personDao.save(person2);
        
    }

}
