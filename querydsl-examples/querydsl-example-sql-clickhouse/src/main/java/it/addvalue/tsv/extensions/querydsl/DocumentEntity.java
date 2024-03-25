/*
 * (C) Copyright 2018 - 2018 - Add Value S.p.A. - All rights reserved.
 */

package it.addvalue.tsv.extensions.querydsl;

import javax.persistence.Entity;

/**
 * @author Add Value S.p.A. by andrea.ferri
 * @version May 24, 2018
 * @since 8.0.0
 */
// TODO: better to harmonize the index name depending on profile. I suggest
// tsv-@{indexPrefix}@{profile}-index, so that we can have:
//
// localhost ==> tsv-localhost-index (indexPrefix empty)
// development ==> tsv-development-index (indexPrefix empty)
// test ==> tsv-machine-test-index (indexPrefix is the machine name)
@Entity(name = "%s")
public class DocumentEntity
    extends TsvEntity
{

	private static final long serialVersionUID = 3748814658395372404L;

	private DocumentEntity()
	{
		super();
	}

	public static DocumentEntity getInstance()
	{
		return Singleton.INSTANCE;
	}

	private static final class Singleton
	{

		public static final DocumentEntity INSTANCE = new DocumentEntity();
	}
}
