/*
 * (C) Copyright 2018 - 2018 - Add Value S.p.A. - All rights reserved.
 */

package it.addvalue.tsv.model.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.addvalue.querydsl.annotations.LinkedEntityPath;
import it.addvalue.tsv.extensions.querydsl.TsvEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.Id;

/**
 * @author Add Value S.p.A. by mirko.scotti
 * @version Feb 27, 2018
 * @since 8.0.0
 */
@LinkedEntityPath(TsvEntity.class)
public class DocumentDao
    implements Serializable
{

	private static final long serialVersionUID = 5055372801717305478L;

	@JsonInclude(Include.NON_NULL)
	@Id
	private String documentId;

	@JsonInclude(Include.NON_NULL)
	private String documentType;

	@JsonInclude(Include.NON_NULL)
	private String plainText;

	@JsonInclude(Include.NON_NULL)
	private String purchaseId;

	@JsonInclude(Include.NON_NULL)
	private String supplierId;

	@JsonInclude(Include.NON_NULL)
	private Date referenceDate;

	private final Map<String, ClassificationDao> classifications = new HashMap<>();

	private final Map<String, String> alphanumericAttributes = new HashMap<>();

	private final Map<String, BigDecimal> decimalAttributes = new HashMap<>();

	private final Map<String, BigInteger> integerAttributes = new HashMap<>();

	/**
	 * Container of attributes of type currency: they are decimal number having fixed 6 decimal digits.
	 */
	private final Map<String, BigDecimal> currencyAttributes = new HashMap<>();

	/**
	 * Container of attributes of type price.
	 */
	private final Map<String, BigDecimal> priceAttributes = new HashMap<>();

	/**
	 * Container for attributes of type date.
	 */
	private final Map<String, Date> dateAttributes = new HashMap<>();

	/**
	 * Container of attributes available for KPI. They are boolean, but values are single characters
	 * rather than true or false.
	 */
	private final Map<String, Boolean> booleanAttributes = new HashMap<>();

	/**
	 * Container of attributes available for KPI. They are boolean, but values are single characters
	 * rather than true or false. This map should be replaced with booleanAttributes.
	 */
	@Deprecated
	private final Map<String, Boolean> kpiAttributes = new HashMap<>();

	public DocumentDao()
	{
		// Nothing to do
	}

	public DocumentDao(final String documentId,
	                   final String purchaseId,
	                   final String documentType,
	                   final String plainText,
	                   final String supplierId,
	                   final Date referenceDate)
	{
		this.documentId = documentId;
		this.purchaseId = Optional.ofNullable(purchaseId).filter(item -> !item.isEmpty()).orElse(null);
		this.documentType = Optional.ofNullable(documentType).filter(item -> !item.isEmpty()).orElse(null);
		this.plainText = Optional.ofNullable(plainText).filter(item -> !item.isEmpty()).orElse(null);
		this.supplierId = Optional.ofNullable(supplierId).filter(item -> !item.isEmpty()).orElse(null);
		this.referenceDate = referenceDate;
	}

	public String getDocumentId()
	{
		return documentId;
	}

	/**
	 * The wrapper method of the {@link #purchaseId} property.
	 *
	 * @return the value of the property
	 */
	public String getPurchaseId()
	{
		return purchaseId;
	}

	public String getDocumentType()
	{
		return documentType;
	}

	public String getPlainText()
	{
		return plainText;
	}

	/**
	 * The wrapper method of the {@link #supplierId} property.
	 *
	 * @return the value of the property
	 */
	public String getSupplierId()
	{
		return supplierId;
	}

	public Map<String, ClassificationDao> getClassifications()
	{
		return classifications;
	}

	public Map<String, String> getAlphanumericAttributes()
	{
		return alphanumericAttributes;
	}

	public Map<String, BigDecimal> getDecimalAttributes()
	{
		return decimalAttributes;
	}

	/**
	 * The wrapper method of the {@link #currencyAttributes} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, BigDecimal> getCurrencyAttributes()
	{
		return currencyAttributes;
	}

	/**
	 * The wrapper method of the {@link #dateAttributes} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, Date> getDateAttributes()
	{
		return dateAttributes;
	}

	/**
	 * The wrapper method of the {@link #booleanAttributes} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, Boolean> getBooleanAttributes()
	{
		return booleanAttributes;
	}

	/**
	 * The wrapper method of the {@link #booleanAttributes} property.
	 *
	 * @return the value of the property
	 */
	@Deprecated
	public Map<String, Boolean> getKpiAttributes()
	{
		return kpiAttributes;
	}

	/**
	 * The wrapper method of the {@link #integerAttributes} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, BigInteger> getIntegerAttributes()
	{
		return integerAttributes;
	}

	/**
	 * The wrapper method of the {@link #priceAttributes} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, BigDecimal> getPriceAttributes()
	{
		return priceAttributes;
	}

	/**
	 * Stores the value of the {@link #documentId} property internally.
	 *
	 * @param documentId
	 *            the property to be stored
	 */
	public void setDocumentId(String documentId)
	{
		this.documentId = documentId;
	}

	/**
	 * Stores the value of the {@link #documentType} property internally.
	 *
	 * @param documentType
	 *            the property to be stored
	 */
	public void setDocumentType(String documentType)
	{
		this.documentType = documentType;
	}

	/**
	 * Stores the value of the {@link #plainText} property internally.
	 *
	 * @param plainText
	 *            the property to be stored
	 */
	public void setPlainText(String plainText)
	{
		this.plainText = plainText;
	}

	/**
	 * Stores the value of the {@link #purchaseId} property internally.
	 *
	 * @param purchaseId
	 *            the property to be stored
	 */
	public void setPurchaseId(String purchaseId)
	{
		this.purchaseId = purchaseId;
	}

	/**
	 * Stores the value of the {@link #supplierId} property internally.
	 *
	 * @param supplierId
	 *            the property to be stored
	 */
	public void setSupplierId(String supplierId)
	{
		this.supplierId = supplierId;
	}

	/**
	 * Not supported by AKW. This method will be soon removed.
	 *
	 * @return a meaningless date
	 */
	@Deprecated
	public Date getTimestamp()
	{
		return new Date();
	}

	/**
	 * Stores the value of the {@link #referenceDate} property internally.
	 *
	 * @param referenceDate
	 *            the property to be stored
	 */
	public void setReferenceDate(Date referenceDate)
	{
		this.referenceDate = referenceDate;
	}

	/**
	 * The wrapper method of the {@link #referenceDate} property.
	 *
	 * @return the value of the property
	 */
	public Date getReferenceDate()
	{
		return referenceDate;
	}

}
