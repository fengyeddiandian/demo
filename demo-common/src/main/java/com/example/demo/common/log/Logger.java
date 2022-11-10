package com.example.demo.common.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.slf4j.LoggerFactory;

/** */
public class Logger {
    private Logger() {

    }

    final static String PATTERN ="%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5level] | %thread | %C.%M - %m%n";

    public static void start(String fileName) {
        // 为false时，返回多个LoggerContext对象，   true：返回唯一的单例LoggerContext
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        Layout<String> layout =PatternLayout.newBuilder().withPattern(PATTERN).withConfiguration(config).build();
        TriggeringPolicy tp = SizeBasedTriggeringPolicy.createPolicy("5MB");
        Appender appender = RollingFileAppender.newBuilder()
                .withFileName("logs/test/" +fileName+".log")
                .withFilePattern("logs/test/" +fileName+".%d{yyyy-MM-dd}.%i.log")
                .withAppend(true)
                .setName(fileName)
                .withPolicy(tp)
                .setLayout(layout)
                .setConfiguration(config)
                .build();
        appender.start();
        config.addAppender(appender);
        AppenderRef ref = AppenderRef.createAppenderRef(fileName, null, null);
        AppenderRef[] refs = new AppenderRef[] {ref};
        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.ALL, fileName, "true", refs, null, config, null);
        loggerConfig.addAppender(appender, null, null);
        config.addLogger(fileName, loggerConfig);
        ctx.updateLoggers();
    }

    public static void stop(String fileName) {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        config.getAppender(fileName).stop();
        config.getLoggerConfig(fileName).removeAppender(fileName);
        config.removeLogger(fileName);
        ctx.updateLoggers();
    }

    /**
     * 获取Logger
     *
     * <p>如果不想使用slf4j,那这里改成直接返回Log4j的Logger即可
     *
     */
    public static org.slf4j.Logger createLogger(String fileName) {
        start(fileName);
        return LoggerFactory.getLogger(fileName);
    }
}
