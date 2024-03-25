/*
 * (C) Copyright 2018 - 2021 - Add Value S.p.A. - All rights reserved.
 */
package it.addvalue.querydsl.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Add Value S.p.A. by mirko.scotti
 * @version May 14, 2021
 * @since 8.3.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LinkedEntityPath
{

	public Class<?> value();
}
