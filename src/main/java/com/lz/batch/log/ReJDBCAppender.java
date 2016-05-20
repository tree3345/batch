package com.lz.batch.log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class ReJDBCAppender extends JDBCAppender {
    
    protected String getLogStatement(LoggingEvent event) {

        String fqnOfCategoryClass = event.fqnOfCategoryClass;
        Category logger = Logger.getRootLogger();
        Priority level = event.getLevel();
        Object message = event.getMessage();
        Throwable throwable = null;
        ReLoggingEvent bEvent = new ReLoggingEvent(fqnOfCategoryClass, logger, level, message, throwable);
        return super.getLogStatement(bEvent);
    }

    public void append(LoggingEvent event) {

        event.getNDC();
        event.getThreadName();
        // Get a copy of this thread's MDC.
        event.getMDCCopy();
        if (super.getLocationInfo()) {
            event.getLocationInformation();
        }
        event.getRenderedMessage();
        event.getThrowableStrRep();
        buffer.add(event);

        if (buffer.size() >= bufferSize) {
            flushBuffer();
        }
    }

    protected Connection getConnection() throws SQLException {

        if (!DriverManager.getDrivers().hasMoreElements())
            setDriver("sun.jdbc.odbc.JdbcOdbcDriver");

        if (connection == null || (connection != null && (connection.isClosed() || !connection.isValid(10)))) {
            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        }

        return connection;
    }

    public void setBufferSize(int newBufferSize) {

        newBufferSize = 1; // TODO
        bufferSize = newBufferSize;
        buffer.ensureCapacity(bufferSize);
        removes.ensureCapacity(bufferSize);
    }
}
