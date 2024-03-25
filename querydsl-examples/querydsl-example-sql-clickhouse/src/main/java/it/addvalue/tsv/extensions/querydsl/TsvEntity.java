/*
 * (C) Copyright 2018 - 2018 - Add Value S.p.A. - All rights reserved.
 */

package it.addvalue.tsv.extensions.querydsl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import it.addvalue.querydsl.extensions.expressions.NoSqlEntityPath;
import it.addvalue.tsv.model.dao.ClassificationDao;
import it.addvalue.tsv.model.dao.DocumentDao;

/**
 * @author Add Value S.p.A. by andrea.ferri
 * @version May 24, 2018
 * @since 8.0.0
 */

public abstract class TsvEntity
    extends NoSqlEntityPath<DocumentDao, String>
{

	private static final long serialVersionUID = -4782311801240469019L;

	/**
	 * The prefix label for mapping currency fields.
	 */
	private static final String CURRENCY_LABEL = "currency";

	/**
	 * The prefix label for mapping price fields.
	 */
	private static final String PRICE_LABEL = "price";

	private final StringPath documentId = createString("documentId");

	private final StringPath documentType = createString("documentType");

	private final StringPath plainText = createString("plainText");

	private final StringPath purchaseId = createString("purchaseId");

	private final StringPath supplierId = createString("supplierId");

	private final DatePath<Date> referenceDate = createDate("referenceDate", Date.class);

	private final Map<String, Path<?>> classifications = new HashMap<>();

	/**
	 * The schema-less decimal field mappings.
	 */
	private final Map<String, NumberPath<BigDecimal>> currencyFields = new HashMap<>();

	/**
	 * The schema-less decimal field mappings.
	 */
	private final Map<String, NumberPath<BigDecimal>> priceFields = new HashMap<>();

	protected TsvEntity()
	{
		super(DocumentDao.class, "Attributes");
	}

	@Override
	public StringPath getId()
	{
		return documentId;
	}

	public StringPath id()
	{
		return documentId;
	}

	/**
	 *
	 * @deprecated Use {@link TsvEntity#getId()} instead
	 */
	@Deprecated
	public StringPath documentId()
	{
		return getId();
	}

	public StringPath documentType()
	{
		return documentType;
	}

	public StringPath plainText()
	{
		return plainText;
	}

	/**
	 * The wrapper method of the {@link #referenceDate} property.
	 *
	 * @return the value of the property
	 */
	public DatePath<Date> referenceDate()
	{
		return referenceDate;
	}

	/**
	 * The wrapper method of the {@link #purchaseId} property.
	 *
	 * @return the value of the property
	 */
	public StringPath purchaseId()
	{
		return purchaseId;
	}

	/**
	 * The wrapper method of the {@link #supplierId} property.
	 *
	 * @return the value of the property
	 */
	public StringPath supplierId()
	{
		return supplierId;
	}

	public Path<?> classifications(String path)
	{
		String[] keys = path.split("\\.");
		if (keys.length <= 1)
		{
			throw new IllegalArgumentException("The field path is not correct. Could missing the map key?");
		}
		String key = keys[0];
		String field = keys[1];
		Path<?> result = classifications.get(key);
		if (result == null)
		{
			String pathPrefix = "classifications.";
			java.lang.reflect.Field m = safeGetField(ClassificationDao.class, field);
			Class<?> type = m.getType();
			if (String.class.isAssignableFrom(type))
			{
				result = createString(pathPrefix + path);
			}
			else if (BigInteger.class.isAssignableFrom(type))
			{
				result = createNumber(pathPrefix + path, BigInteger.class);
			}
			else if (BigDecimal.class.isAssignableFrom(type))
			{
				result = createNumber(pathPrefix + path, BigDecimal.class);
			}
			else if (Date.class.isAssignableFrom(type))
			{
				result = createDate(pathPrefix + path, Date.class);
			}
			else if (Boolean.class.isAssignableFrom(type))
			{
				result = createBoolean(pathPrefix + path);
			}
			else if (String[].class.isAssignableFrom(type))
			{
				result = createArray(pathPrefix + path, String[].class);
			}
			else if (Integer.class.isAssignableFrom(type))
			{
				result = createNumber(pathPrefix + path, Integer.class);
			}
			else if (Map.class.isAssignableFrom(type))
			{
				if (field.equals("levels"))
				{
					result = createString(pathPrefix + path);
				}
			}
			else
			{
				throw new UnsupportedOperationException("Unsupported classification type: " + type);
			}
			classifications.put(path, result);
		}
		return result;
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #alphanumericFields(String)} instead
	 */
	@Deprecated
	public StringPath alphanumericAttributes(String fieldName)
	{
		return alphanumericFields(fieldName);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #integerFields(String)} instead
	 */
	@Deprecated
	public NumberPath<BigInteger> integerAttributes(String fieldName)
	{
		return integerFields(fieldName);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #decimalFields(String)} instead
	 */
	@Deprecated
	public NumberPath<BigDecimal> decimalAttributes(String fieldName)
	{
		return decimalFields(fieldName);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #dateFields(String)} instead
	 */
	@Deprecated
	public DatePath<Date> dateAttributes(String fieldName)
	{
		return dateFields(fieldName);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #booleanFields(String)} instead
	 */
	@Deprecated
	public BooleanPath booleanAttributes(String fieldName)
	{
		return booleanFields(fieldName);
	}

	public NumberPath<BigDecimal> currencyFields(String fieldName)
	{
		return currencyFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, CURRENCY_LABEL)),
		                                      item -> createNumber(CURRENCY_LABEL + fieldLabel + '.' + item, BigDecimal.class));
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #currencyFields(String)} instead
	 */
	@Deprecated
	public NumberPath<BigDecimal> currencyAttributes(String fieldName)
	{
		return currencyFields(fieldName);
	}

	public NumberPath<BigDecimal> priceFields(String fieldName)
	{
		return priceFields.computeIfAbsent(Objects.requireNonNull(fieldName, String.format(ERROR_MESSAGE, PRICE_LABEL)),
		                                   item -> createNumber(PRICE_LABEL + fieldLabel + '.' + item, BigDecimal.class));
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 * @deprecated Use {@link #priceFields(String)} instead
	 */
	@Deprecated
	public NumberPath<BigDecimal> priceAttributes(String fieldName)
	{
		return priceFields(fieldName);
	}

	private java.lang.reflect.Field safeGetField(Class<ClassificationDao> dao, String field)
	{
		try
		{
			return dao.getDeclaredField(field);
		}
		catch (SecurityException | NoSuchFieldException x)
		{
			throw new IllegalArgumentException("An error occurs during the retrieving method:", x);
		}
	}
}
