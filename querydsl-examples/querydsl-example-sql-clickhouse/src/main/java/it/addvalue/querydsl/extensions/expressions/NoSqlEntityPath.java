/*
 * (C) Copyright 2018 - 2021 - Add Value S.p.A. - All rights reserved.
 */
package it.addvalue.querydsl.extensions.expressions;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import it.addvalue.querydsl.annotations.LinkedEntityPath;

//import it.addvalue.querydsl.annotations.LinkedEntityPath;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Add Value S.p.A. by mirko.scotti
 * @version Jan 23, 2021
 * @since 8.3.0
 */
public abstract class NoSqlEntityPath<T, I extends Comparable<? super I> & Serializable>
    extends EntityPathBase<T>
{

	/**
	 * The serialization number.
	 */
	private static final long serialVersionUID = 5382942365719931259L;

	/**
	 * The error message to be displayed when a mapping method is invoked and the field name is not
	 * specified.
	 */
	protected static final String ERROR_MESSAGE = "Please, provide a valid %s field name.";

	/**
	 * The field's default mapping label.
	 */
	protected static final String FIELD_LABEL = "Fields";

	/**
	 * The prefix label for mapping alphanumeric fields.
	 */
	private static final String ALPHANUMERIC_LABEL = "alphanumeric";

	/**
	 * The prefix label for mapping integer fields.
	 */
	private static final String INTEGER_LABEL = "integer";

	/**
	 * The prefix label for mapping decimal fields.
	 */
	private static final String DECIMAL_LABEL = "decimal";

	/**
	 * The prefix label for mapping date fields.
	 */
	private static final String DATE_LABEL = "date";

	/**
	 * The prefix label for mapping date/time fields.
	 */
	private static final String DATETIME_LABEL = "datetime";

	/**
	 * The prefix label for mapping boolean fields.
	 */
	private static final String BOOLEAN_LABEL = "boolean";

	/**
	 * The field's mapping label configured for this entity. It is used as suffix of typed labels. When
	 * not set, the value of {@link #FIELD_LABEL} is assumed.
	 *
	 * @see NoSqlEntityPath#FIELD_LABEL
	 * @see NoSqlEntityPath#ALPHANUMERIC_LABEL
	 * @see NoSqlEntityPath#INTEGER_LABEL
	 * @see NoSqlEntityPath#DECIMAL_LABEL
	 * @see NoSqlEntityPath#DATE_LABEL
	 * @see NoSqlEntityPath#DATETIME_LABEL
	 * @see NoSqlEntityPath#BOOLEAN_LABEL
	 */
	protected final String fieldLabel;

	/**
	 * The schema-less alphanumeric field mappings.
	 */
	private final Map<String, StringPath> alphanumericFields = new HashMap<>();

	/**
	 * The schema-less integer field mappings.
	 */
	private final Map<String, NumberPath<BigInteger>> integerFields = new HashMap<>();

	/**
	 * The schema-less decimal field mappings.
	 */
	private final Map<String, NumberPath<BigDecimal>> decimalFields = new HashMap<>();

	/**
	 * The schema-less date field mappings.
	 */
	private final Map<String, DatePath<Date>> dateFields = new HashMap<>();

	/**
	 * The schema-less date/time field mappings.
	 */
	private final Map<String, DatePath<LocalDateTime>> datetimeFields = new HashMap<>();

	/**
	 * The schema-less boolean field mappings.
	 */
	private final Map<String, BooleanPath> booleanFields = new HashMap<>();

	/**
	 * The initializer setting the type of the entity object and a default mapping .
	 *
	 * @param type
	 *            the type of the entity representing the archive to access
	 */
	protected NoSqlEntityPath(Class<? extends T> type)
	{
		this(type, null);
	}

	/**
	 * The initializer setting the type of the entity object whose metadata are defined by this entity.
	 *
	 * @param type
	 *            the type of the entity representing the archive to access
	 * @param fieldLabel
	 *            the suffix of the internal prefix field names
	 */
	protected NoSqlEntityPath(Class<? extends T> type, String fieldLabel)
	{
		super(type,
		      Optional.ofNullable(type)
		              .filter(item -> item.isAnnotationPresent(LinkedEntityPath.class))
		              .map(item -> type.getSimpleName().toLowerCase())
		              .orElseThrow(() -> new IllegalArgumentException(String.format("Please, provide a valid entity type: it must be annotated by %s",
		                                                                            LinkedEntityPath.class.getName()))));
		Class<?> linkedEntity = type.getAnnotation(LinkedEntityPath.class).value();
		Optional.of(linkedEntity)
		        .filter(item -> item.isAssignableFrom(getClass()))
		        .orElseThrow(() -> new IllegalArgumentException(String.format("Linked entity of class %s does not match this class: expected %s, found %s",
		                                                                      type.getName(),
		                                                                      getClass().getName(),
		                                                                      linkedEntity.getName())));
		this.fieldLabel = Optional.ofNullable(fieldLabel).orElse(FIELD_LABEL);
	}

	/**
	 * The wrapper method of the {@link #id} property.
	 *
	 * @return the value of the property
	 */
	public abstract Path<I> getId();

	/**
	 * Provides a QueryDSL expression representing an alphanumeric field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public StringPath alphanumericFields(String fieldName)
	{
		return alphanumericFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, ALPHANUMERIC_LABEL)),
		                                          item -> createString(ALPHANUMERIC_LABEL + fieldLabel + '.' + item));
	}

	/**
	 * Provides a QueryDSL expression representing an integer field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public NumberPath<BigInteger> integerFields(String fieldName)
	{
		return integerFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, INTEGER_LABEL)),
		                                     item -> createNumber(INTEGER_LABEL + fieldLabel + '.' + item, BigInteger.class));
	}

	/**
	 * Provides a QueryDSL expression representing a decimal field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public NumberPath<BigDecimal> decimalFields(String fieldName)
	{
		return decimalFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, DECIMAL_LABEL)),
		                                     item -> createNumber(DECIMAL_LABEL + fieldLabel + '.' + item, BigDecimal.class));
	}

	/**
	 * Provides a QueryDSL expression representing a date field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public DatePath<Date> dateFields(String fieldName)
	{
		return dateFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, DATE_LABEL)),
		                                  item -> createDate(DATE_LABEL + fieldLabel + '.' + item, Date.class));
	}

	/**
	 * Provides a QueryDSL expression representing a date/time field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public DatePath<LocalDateTime> datetimeFields(String fieldName)
	{
		return datetimeFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, DATETIME_LABEL)),
		                                      item -> createDate(DATETIME_LABEL + fieldLabel + '.' + item, LocalDateTime.class));
	}

	/**
	 * Provides a QueryDSL expression representing a boolean field in the NOSQL archive.
	 *
	 * @param fieldName
	 *            the name of the field
	 * @return the QueryDSL's path object mapping the given field name
	 */
	public BooleanPath booleanFields(String fieldName)
	{
		return booleanFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, BOOLEAN_LABEL)),
		                                     item -> createBoolean(BOOLEAN_LABEL + fieldLabel + '.' + item));
	}
}
