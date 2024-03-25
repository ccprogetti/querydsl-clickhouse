package com.querydsl.example.dao;

import com.querydsl.core.Tuple;
import com.querydsl.example.dto.Person;

import it.addvalue.tsv.model.dao.DocumentDao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class PersonDaoTest extends AbstractDaoTest {

    @Autowired
    PersonDao personDao;

    @Test
    public void findAll() {
        List<?> persons = personDao.findAllDocuments();
        assertFalse(persons.isEmpty());
    }

    @Test
    public void findById() {
        assertNotNull(personDao.findById(1));
    }

    @Test
    public void update() {
        Person person = personDao.findById(1);
        personDao.save(person);
    }

    @Test
    public void delete() {
        Person person = new Person();
        person.setEmail("john@acme.com");
        personDao.save(person);
        assertNotNull(person.getId());
        personDao.delete(person);
        assertNull(personDao.findById(person.getId()));
    }

}
