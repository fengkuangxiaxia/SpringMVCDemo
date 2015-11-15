package com.gaussic.EJBtools;

import com.gaussic.HelloWorld;
import com.gaussic.HelloWorldBean;

import javax.ejb.EJBObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by Cole Fu on 2015/11/15.
 */
public class EjbTools {

    private static final String PROPERTY_FILE_NAME = "EjbTools.properties";
    private static Properties props;

    private synchronized void loadProperties() throws IOException {
        if(props == null){
            java.io.InputStream inputstream = ClassLoader.getSystemClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            if (inputstream == null){
                inputstream = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
                if (inputstream == null){
                    throw new IOException("Read properties file error:" + PROPERTY_FILE_NAME);
                }
            }
            props = new Properties();
            props.load(inputstream);
        }
    }


    public InitialContext getInitialContext() throws NamingException, IOException {
        loadProperties();

        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", props.getProperty("jndi.initialContextFactory"));
        properties.put("java.naming.provider.url", props.getProperty("jndi.providerUrl"));
        properties.put("java.naming.security.principal", props.getProperty("jndi.securityPrincipal"));
        properties.put("java.naming.security.credentials", props.getProperty("jndi.securityCredentials"));
        InitialContext initialcontext = new InitialContext(properties);
        return initialcontext;
    }


    public static EJBObject getEJBObject(String s) throws NamingException, IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        EjbTools tool = new EjbTools();
        InitialContext initialcontext = tool.getInitialContext();

        Object home = initialcontext.lookup(s);
        Class homeClass = home.getClass();

        home = PortableRemoteObject.narrow(home, homeClass);
        Method method = homeClass.getMethod("create", null);

        EJBObject ejb = (EJBObject)method.invoke(home, null);
        return ejb;

    }

    public static EJBObject getEJBObject2(String s) throws NamingException, IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        EjbTools tool = new EjbTools();
        InitialContext initialcontext = tool.getInitialContext();
        // The app name is the application name of the deployed EJBs. This is typically the ear name
        // without the .ear suffix. However, the application name could be overridden in the application.xml of the
        // EJB deployment on the server.
        // Since we haven't deployed the application as a .ear, the app name for us will be an empty string
        final String appName = "EAR";
        // This is the module name of the deployed EJBs on the server. This is typically the jar name of the
        // EJB deployment, without the .jar suffix, but can be overridden via the ejb-jar.xml
        // In this example, we have deployed the EJBs in a jboss-as-ejb-remote-app.jar, so the module name is
        // jboss-as-ejb-remote-app
        final String moduleName = "EJB";
        // AS7 allows each deployment to have an (optional) distinct name. We haven't specified a distinct name for
        // our EJB deployment, so this is an empty string
        final String distinctName = "";
        // The EJB name which by default is the simple class name of the bean implementation class
        final String beanName = HelloWorldBean.class.getSimpleName();
        // the remote view fully qualified class name
        final String viewClassName = HelloWorld.class.getName();
        // let's do the lookup
        return (EJBObject) initialcontext.lookup("ejb:" + appName + "/" + moduleName /*+ "/" + distinctName*/ + "/" + beanName + "!" + viewClassName);

    }

}
