package com.lz.batch.log;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

@SuppressWarnings("serial")
public class ReLoggingEvent extends LoggingEvent {

    public ReLoggingEvent(String fqnOfCategoryClass, Category logger, Priority level, Object message, Throwable throwable) {

        super(fqnOfCategoryClass, logger, level, message, throwable);
    }

    /**
     * 线程名单引号做处理
     * 
     * @see org.apache.log4j.spi.LoggingEvent#getThreadName()
     */
    public String getThreadName() {

        String thrdName = super.getThreadName();
        if (thrdName.indexOf("'") != -1)
            thrdName = thrdName.replaceAll("'", "''");
        return thrdName;
    }

    /**
     * 对插入的message中包含的单引号做处理
     * 
     * @see org.apache.log4j.spi.LoggingEvent#getRenderedMessage()
     */
    public String getRenderedMessage() {

        String renderedMessage = super.getRenderedMessage();
        if (renderedMessage.indexOf("'") != -1)
            renderedMessage = renderedMessage.replaceAll("'", "''");
        return renderedMessage;
    }
}
