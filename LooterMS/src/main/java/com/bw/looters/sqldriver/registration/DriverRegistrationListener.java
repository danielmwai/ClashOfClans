/*
 * To change this license header, choose License Headers in Project
/ Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bw.looters.sqldriver.registration;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Registers and unregisters the Mysql JDBC driver.
 *
 * Use only when the mysql jar is deployed inside the webapp (not as an
 * appserver lib)
 */
public class DriverRegistrationListener implements ServletContextListener {

    private static final Logger log = LoggerFactory
            .getLogger(DriverRegistrationListener.class);

    private Driver driver = null;

    /**
     * Registers the Oracle JDBC driver
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initialising Context...");
    }

    /**
     * Deregisters JDBC driver
     *
     * Prevents Tomcat 7 from complaining about memory leaks.
     */
    @Override
    public final void contextDestroyed(ServletContextEvent sce) {

        log.info("Destroying Context...");

        try {
            log.info("Calling MySQL AbandonedConnectionCleanupThread shutdown");
            com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();

        } catch (InterruptedException e) {
            log.error("Error calling MySQL AbandonedConnectionCleanupThread shutdown {}",
                    e);
        }

        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();

            if (driver.getClass().getClassLoader() == cl) {

                try {
                    log.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);

                } catch (SQLException ex) {
                    log.error("Error deregistering JDBC driver {}", driver, ex);
                }

            } else {
                log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);

            }
        }
    }

}
