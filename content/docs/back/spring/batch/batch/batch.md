---
weight: 1
slug: index
date: 2022-03-26
title: "Spring Batch(스프링 배치)"
description: "Spring Batch 스프링 배치 가이드"
toc: true
---

## 시작하기


### 의존성 모듈 설정

```
// build.gradle

implementation 'org.springframework.boot:spring-boot-starter-batch'
```

### @EnableBatchProcessing 어노테이션 설정

`@EnableBatchProcessing` 배치 동작에 필요한 기본적인 설정들을 등록합니다.

`@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })`
배치를 사용하기 위해 DataSource 설정 없이 스프링 부트를 동작 시킬 수 있습니다.


```
// /src/main/java/com/jjamong/batch/BatchApplication.java

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableBatchProcessing
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
```

### Job, Step 추가


```
// /src/main/java/com/jjamong/batch/IndexController.java

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class IndexController {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobBatch() {
        return jobBuilderFactory.get("jobEtl")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>> step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
```


### 실행 

스프링 부트 시작 시 컨텍스트에 등록된 모든 Spring Batch 작업을 실행합니다.
시작시에 실행되지 않게하려면 application.properties 파일에 spring.batch.job.enabled=false 를 설정해주면 됩니다.


```
2022-03-27 01:26:11.001  INFO 14884 --- [  restartedMain] com.jjamong.batch.BatchApplication       : No active profile set, falling back to 1 default profile: "default"
2022-03-27 01:26:11.285  INFO 14884 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-03-27 01:26:11.287  INFO 14884 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-03-27 01:26:11.287  INFO 14884 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.60]
2022-03-27 01:26:11.316  INFO 14884 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-03-27 01:26:11.317  INFO 14884 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 312 ms
2022-03-27 01:26:11.484  INFO 14884 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-03-27 01:26:11.498  INFO 14884 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-03-27 01:26:11.502  INFO 14884 --- [  restartedMain] com.jjamong.batch.BatchApplication       : Started BatchApplication in 0.537 seconds (JVM running for 30.796)
2022-03-27 01:26:11.504  INFO 14884 --- [  restartedMain] o.s.b.a.b.JobLauncherApplicationRunner   : Running default command line with: []
2022-03-27 01:26:11.505  WARN 14884 --- [  restartedMain] o.s.b.c.c.a.DefaultBatchConfigurer       : No datasource was provided...using a Map based JobRepository
2022-03-27 01:26:11.506  WARN 14884 --- [  restartedMain] o.s.b.c.c.a.DefaultBatchConfigurer       : No transaction manager was provided, using a ResourcelessTransactionManager
2022-03-27 01:26:11.512  INFO 14884 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : No TaskExecutor has been set, defaulting to synchronous executor.
2022-03-27 01:26:11.517  INFO 14884 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] launched with the following parameters: [{}]
2022-03-27 01:26:11.523  INFO 14884 --- [  restartedMain] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
>>> step1
2022-03-27 01:26:11.533  INFO 14884 --- [  restartedMain] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 8ms
2022-03-27 01:26:11.540  INFO 14884 --- [  restartedMain] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] completed with the following parameters: [{}] and the following status: [COMPLETED] in 21ms  
2022-03-27 01:26:11.544  INFO 14884 --- [  restartedMain] .ConditionEvaluationDeltaLoggingListener : Condition evaluation unchanged
```

## Cron과 연동해서 시작하기


### @EnableScheduling 어노테이션 설정

```
// /src/main/java/com/jjamong/batch/BatchApplication.java

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class BatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}

```


### cron, batch run 추가

```
// /src/main/java/com/jjamong/batch/IndexController.java

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.launch.JobLauncher;

@Controller
public class IndexController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job jobBatch() {
        return jobBuilderFactory.get("jobEtl")
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println(">>> step1");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @RequestMapping("/")
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> output = new HashMap<>();

        final ScheduledFuture<?> task = threadPoolTaskScheduler.schedule(() -> {
            try {
                final JobParametersBuilder builder = new JobParametersBuilder();
                builder.addString("jobId", String.valueOf(System.currentTimeMillis())); // 필수
                jobLauncher.run(jobBatch(), builder.toJobParameters());
            } catch (final Exception e) {
            }
        }, new CronTrigger("0/5 * * * * *"));

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
```


### 실행 

http://localhost:8080/  접속 하면 배치가 실행 되고, cron 설정에 따라 5초에 한번씩 호출 되는 것을 확인 할 수 있습니다.


```
2022-03-27 02:42:20.713  INFO 1536 --- [  restartedMain] com.jjamong.batch.BatchApplication       : No active profile set, falling back to 1 default profile: "default"
2022-03-27 02:42:20.782  INFO 1536 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2022-03-27 02:42:20.783  INFO 1536 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2022-03-27 02:42:22.036  INFO 1536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2022-03-27 02:42:22.049  INFO 1536 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2022-03-27 02:42:22.050  INFO 1536 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.60]
2022-03-27 02:42:22.144  INFO 1536 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2022-03-27 02:42:22.144  INFO 1536 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1360 ms
2022-03-27 02:42:22.907  INFO 1536 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2022-03-27 02:42:22.971  INFO 1536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-03-27 02:42:22.994  INFO 1536 --- [  restartedMain] com.jjamong.batch.BatchApplication       : Started BatchApplication in 2.713 seconds (JVM running for 3.381)
2022-03-27 02:42:34.406  INFO 1536 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2022-03-27 02:42:34.406  INFO 1536 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2022-03-27 02:42:34.408  INFO 1536 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2022-03-27 02:42:35.011  WARN 1536 --- [   scheduling-1] o.s.b.c.c.a.DefaultBatchConfigurer       : No datasource was provided...using a Map based JobRepository
2022-03-27 02:42:35.011  WARN 1536 --- [   scheduling-1] o.s.b.c.c.a.DefaultBatchConfigurer       : No transaction manager was provided, using a ResourcelessTransactionManager
2022-03-27 02:42:35.029  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : No TaskExecutor has been set, defaulting to synchronous executor.
2022-03-27 02:42:35.070  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] launched with the following parameters: [{jobId=1648316555009}]
2022-03-27 02:42:35.103  INFO 1536 --- [   scheduling-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
>>> step1
2022-03-27 02:42:35.123  INFO 1536 --- [   scheduling-1] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 19ms
2022-03-27 02:42:35.134  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] completed with the following parameters: [{jobId=1648316555009}] and the following status: [COMPLETED] in 38ms
2022-03-27 02:42:40.005  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] launched with the following parameters: [{jobId=1648316560002}]
2022-03-27 02:42:40.009  INFO 1536 --- [   scheduling-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
>>> step1
2022-03-27 02:42:40.013  INFO 1536 --- [   scheduling-1] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 3ms
2022-03-27 02:42:40.017  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] completed with the following parameters: [{jobId=1648316560002}] and the following status: [COMPLETED] in 11ms
2022-03-27 02:42:45.018  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] launched with the following parameters: [{jobId=1648316565016}]
2022-03-27 02:42:45.020  INFO 1536 --- [   scheduling-1] o.s.batch.core.job.SimpleStepHandler     : Executing step: [step1]
>>> step1
2022-03-27 02:42:45.028  INFO 1536 --- [   scheduling-1] o.s.batch.core.step.AbstractStep         : Step: [step1] executed in 8ms
2022-03-27 02:42:45.032  INFO 1536 --- [   scheduling-1] o.s.b.c.l.support.SimpleJobLauncher      : Job: [SimpleJob: [name=jobEtl]] completed with the following parameters: [{jobId=1648316565016}] and the following status: [COMPLETED] in 12ms
```