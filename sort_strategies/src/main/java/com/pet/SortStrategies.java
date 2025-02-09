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
        int[] array = {-1, 5, 25, 7, 5, 10, 8, 12};
        int[] shortArray = {-1, 5, 25, 7};
        ConfigurableApplicationContext context = SpringApplication.run(SortStrategies.class, args);
        SorterService service = context.getBean(SorterService.class);
        System.out.println("Sort normal array:");
        service.addSortedByQuickSort(array);
        service.addSortedByMergeSort(array);
        System.out.println("Sort short array");
        service.addSortedByQuickSort(shortArray);
        service.addSortedByMergeSort(shortArray);
    }
}
