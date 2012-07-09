/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.naming.factory;

import java.net.URL;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * Simple object factory which returns an URL object build from a system
 * property value having the same name as the looked-up object. Mainly usable as
 * a workaround in EAP6/AS7 to register an URL object in JNDI.
 * 
 * Example usage: Register an object-factory in JNDI with a given name. Then add
 * a system-property with the same name and the desired url as value.
 * 
 * /subsystem=naming/binding=java\:jboss\/exported\/myurl:add(binding-type=
 * object-factory, module=org.jboss.as.naming.factory ,
 * class=org.jboss.as.naming.factory.URLResourceFactory) <br/>
 * 
 * /system-property=java\:jboss\/exported\/myurl:add(value=http://www.myurl.com)
 * 
 * @author Akram Ben Aissi
 * 
 */
public class URLResourceFactory implements ObjectFactory {

    public Object getObjectInstance(Object obj, Name name, Context ctx,
	    Hashtable<?, ?> env) throws Exception {
	String property = System.getProperty(obj.toString());
	URL url = new URL(property);
	return url;
    }
}
