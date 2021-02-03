package org.toby;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOG = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        LOG.debug("This is an debug log");
        LOG.info("This is an info log");
        LOG.warn("This is an warn log");
        LOG.error("This is an error log");
        LOG.fatal("This is an fatal log");
        LOG.trace("This is an trace log");




    }
}
