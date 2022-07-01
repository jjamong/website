---
weight: 1
slug: index
date: 2022-06-30
title: "log(로그)"
description: "log(로그)"
toc: true
---

## 이해하기

Spring Boot의 기본 로그 툴은 logback 입니다.

System.out.println()은 IO 리소스를 많이 사용하여 속도가 느리고<br>
로그를 파일로 저장하는 것이 불가능 합니다.

이러한 점 때문에 로그를 잘 활용해야 합니다.

#### log4j
Apache 개발하여 2015년을 끝으로 개발 중단되었습니다.<br>
이전까지, 그리고 현재도 이전 소스 프로젝트 들은 표준으로 가장 많이 사용되던 라이브러리입니다. 

#### logback 
log4j 이후 같은 개발자가 개발하여 보다 향상된 성능(속도, 메모리 효율성)과<br>
Slf4j로 Spring Boot에서는 spring-boot-starter-logging안에 기본적으로 포함되어 있어서<br>
따로 dependency를 추가하지 않고 사용 가능

#### Slf4j (Simple Logging Facade for Java)
다른 로그 라이브러리(log4j, logback 등)들을 통합해서<br>
인터페이스로 제공하는 하는 라이브러리 입니다.


## 시작하기

logback은 기본적으로 logback.xml파일을 로드하도록 되어 있습니다.<br>
Spring Boot에서는 logback-spring.xml파일을 로드합니다.


```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!--로그 파일 저장 위치-->
    <property name="LOGS_PATH" value="./logs"/>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%d{HH:mm} %-5level %logger{36} - %msg%n</Pattern>
        </layout>
    </appender>
    <appender name="SAMPLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>
    <appender name="DAILY_ROLLING_FILE_APPENDER" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOGS_PATH}/logback.log</file>
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOGS_PATH}/logback.%d{yyyy-MM-dd}.%i.log.gz</fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <!-- or whenever the file size reaches 100MB -->
                <maxFileSize>5MB</maxFileSize>
                <!-- kb, mb, gb -->
            </timeBasedFileNamingAndTriggeringPolicy>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>
    
    <logger name="com.gaeyou.firstproject" level="DEBUG">
        <appender-ref ref="SAMPLE" />
    </logger>
    <logger name="com.gaeyou.firstproject" level="INFO">
        <appender-ref ref="DAILY_ROLLING_FILE_APPENDER" />
    </logger>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```