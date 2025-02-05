/* Слоистая архитектура - это архитектурный стиль, при котором приложение разделяется на
 * логические уровни. Обычно: presentation -> business -> data */

package com.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SortStrategies {
    @Autowired

    public static void main(String[] args) {
        SpringApplication.run(SortStrategies.class, args);
    }
}
