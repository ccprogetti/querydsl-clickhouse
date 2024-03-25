/*
 * (C) Copyright 2018 - 2018 - Add Value S.p.A. - All rights reserved.
 */

package it.addvalue.tsv.model.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Add Value S.p.A. by mirko.scotti
 * @version Feb 27, 2018
 * @since 8.0.0
 */
public class ClassificationDao
    implements Serializable
{

	private static final long serialVersionUID = -5808234912294487774L;

	@JsonInclude(Include.NON_NULL)
	private String taxonomy;

	private String category;

	@JsonInclude(Include.NON_NULL)
	private String[] path;

	// TODO da rimuovere. Capire come leggere gli array da postgres ed elasticsearch
	@JsonInclude(Include.NON_NULL)
	private String stringPath;

	@JsonInclude(Include.NON_NULL)
	private BigInteger depth;

	@JsonInclude(Include.NON_NULL)
	private Boolean properItem;

	private final Map<String, String> levels = new HashMap<>();

	public String getTaxonomy()
	{
		return taxonomy;
	}

	public void setTaxonomy(String taxonomy)
	{
		this.taxonomy = taxonomy;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String[] getPath()
	{
		return path;
	}

	public void setPath(String[] path)
	{
		this.path = path;
	}

	public BigInteger getDepth()
	{
		return depth;
	}

	public void setDepth(BigInteger depth)
	{
		this.depth = depth;
	}

	/**
	 * The wrapper method of the {@link #stringPath} property.
	 *
	 * @return the value of the property
	 */
	public String getStringPath()
	{
		return stringPath;
	}

	/**
	 * Stores the value of the {@link #stringPath} property internally.
	 *
	 * @param stringPath
	 *            the property to be stored
	 */
	public void setStringPath(String stringPath)
	{
		this.stringPath = stringPath;
	}

	/**
	 * The wrapper method of the {@link #properItem} property.
	 *
	 * @return the value of the property
	 */
	public Boolean getProperItem()
	{
		return properItem;
	}

	/**
	 * Stores the value of the {@link #properItem} property internally.
	 *
	 * @param properItem
	 *            the property to be stored
	 */
	public void setProperItem(Boolean properItem)
	{
		this.properItem = properItem;
	}

	/**
	 * The wrapper method of the {@link #levels} property.
	 *
	 * @return the value of the property
	 */
	public Map<String, String> getLevels()
	{
		return levels;
	}

}
