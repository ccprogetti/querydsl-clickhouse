## Querydsl Customer DAO

Querydsl Customer DAO is an example project that demonstrates some best practices on how to use Querydsl SQL on the DAO level in Spring projects.

Compared to direct JDBC usage Querydsl SQL is typesafe, closer to SQL and abstracts over SQL dialect specific differences.

This example project presents a version of Querydsl SQL usage where no generated bean types are used, but external DTO types are populated from queries.



CREATE TABLE person (
id UInt64,
first_name varchar(64),
last_name varchar(64),
phone varchar(64),
email varchar(64)
)
ENGINE = MergeTree
ORDER BY id