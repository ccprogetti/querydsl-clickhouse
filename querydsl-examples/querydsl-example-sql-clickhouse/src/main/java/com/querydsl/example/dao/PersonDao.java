package com.querydsl.example.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.example.dto.Person;

import it.addvalue.tsv.model.dao.DocumentDao;

import java.util.List;

public interface PersonDao {

    Person findById(long id);

    List<Person> findAll(Predicate... where);

    List<?> findAllDocuments(Predicate... where);

    Person save(Person p);

    long count();

    void delete(Person p);

}
