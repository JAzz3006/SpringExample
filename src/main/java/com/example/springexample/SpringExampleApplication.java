package com.example.springexample;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.net.URL;

@SpringBootApplication
public class SpringExampleApplication    {
    public static void main(String[] args) {
        // Проверяем, видит ли Spring Boot файл     changelog в classpath
        URL changelog = SpringExampleApplication.class
                .getClassLoader()
                .getResource("db/changelog/db.changelog-master.xml");

        if (changelog != null) {
            System.out.println("✅ Liquibase changelog найден по пути: " + changelog);
        } else {
            System.err.println("❌ Liquibase changelog НЕ найден! Проверь путь и структуру папок.");
        }

        //SpringApplication.run(SpringExampleApplication.class, args);
        ApplicationContext context = SpringApplication.run(SpringExampleApplication.class, args);
    }
}