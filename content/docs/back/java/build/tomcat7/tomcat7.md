---
weight: 1
slug: index
date: 2023-02-08
title: "tomcat7-maven-plugin"
description: "tomcat7-maven-plugin"
toc: true
---

maven을 통해 톰캣을 빌드 할 수 있습니다.
<br>일반적인 tomcat을 연동해서 활용하는 것보다 빠른 장점이 있습니다.

```
// pom.xml

<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <port>8080</port>
        <path>/</path>
        <systemProperties>
            <JAVA_OPTS>-Xms1024m -Xmx1024m --XX:MaxPermSize=512m</JAVA_OPTS>
        </systemProperties>
    </configuration>
</plugin>
```

```
mvn tomcat7:run
```