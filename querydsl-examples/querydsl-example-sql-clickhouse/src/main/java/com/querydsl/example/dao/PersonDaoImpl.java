package com.querydsl.example.dao;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.QBean;
import com.querydsl.example.dto.Person;
import com.querydsl.sql.SQLQueryFactory;

import it.addvalue.tsv.extensions.querydsl.DocumentEntity;
import it.addvalue.tsv.model.dao.DocumentDao;

import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Projections.bean;
import static com.querydsl.example.sql.QPerson.person;

@Transactional
public class PersonDaoImpl implements PersonDao {

    @Inject
    SQLQueryFactory queryFactory;

    //private DocumentEntity documentEntity = new DocumentDao();

    final QBean<Person> personBean = bean(Person.class, person.all());
    
    final QBean<DocumentDao> documentBean = bean(DocumentDao.class, DocumentEntity.getInstance() );

    @Override
    public Person findById(long id) {
        return queryFactory.select(personBean)
                .from(person)
                .where(person.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<?> findAllDocuments(Predicate... where) {
        List<Expression<?>> projection = new ArrayList<>(3);
		List<OrderSpecifier<?>> order = new ArrayList<>(2);

        // if (detailExpression != null)
		// {
		// 	projection.add(detailExpression);
		// 	order.add(OrderSpecifier.class.getConstructor(Order.class, Expression.class).newInstance(Order.ASC, detailExpression));
		// }

        //projection.add(entityExpression);
		//projection.add(weightExpression);
	
        //return queryFactory.select(documentBean)
        return queryFactory.select(DocumentEntity.getInstance().alphanumericFields("ciao")) 
        .from(DocumentEntity.getInstance().getRoot())
        .where(where)
        //.groupBy(projection.subList(0, projection.size() - 1).toArray(new Expression<?>[0]))
        .orderBy(order.toArray(new OrderSpecifier<?>[0]))
        .fetch();
    
    }

    @Override
    public List<Person> findAll(Predicate... where) {
        return queryFactory.select(personBean)
                .from(person)
                .where(where)
                .fetch();
    }

    @Override
    public Person save(Person p) {
        Long id = p.getId();

        if (id == null) {
            id = queryFactory.insert(person)
                .populate(p).execute();
            p.setId(id);
        } else {
            queryFactory.update(person)
                .populate(p)
                .where(person.id.eq(id)).execute();
        }

        return p;
    }

    @Override
    public long count() {
        return queryFactory.from(person).fetchCount();
    }

    @Override
    public void delete(Person p) {
        queryFactory.delete(person)
            .where(person.id.eq(p.getId()))
            .execute();
    }

}
