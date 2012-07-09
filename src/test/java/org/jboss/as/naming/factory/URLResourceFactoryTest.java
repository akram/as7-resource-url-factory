package org.jboss.as.naming.factory;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class URLResourceFactoryTest {

    public final static String JNDI_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";

    public static void main(String[] args) throws Exception {

	Properties jndiProps = new Properties();
	jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
		"org.jboss.naming.remote.client.InitialContextFactory");
	jndiProps.put(Context.PROVIDER_URL, "remote://localhost:4447");
	// To make this work, add a user "akram" in your Application Realm (and
	// not the Management Realm) using the add-user.sh JBoss Script.
	jndiProps.put(Context.SECURITY_PRINCIPAL, "akram");
	jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
	// create a context passing these properties
	Context ctx = new InitialContext(jndiProps);
	// lookup
	Object lookup = ctx.lookup("url");
	System.out.print(lookup);
    }
}
