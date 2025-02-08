/* Слоистая архитектура - это архитектурный стиль, при котором приложение разделяется на
 * логические уровни. Обычно: presentation -> business -> data */

package com.pet;

import com.pet.service.SorterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SortStrategies {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SortStrategies.class, args);
        try {
            context.getBean(SorterService.class).addSortedByQuickSort(null);
        } catch (Throwable ex) {
            System.out.println("Test afterThrows");
        }
    }
}
