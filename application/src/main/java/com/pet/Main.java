/* Гексогональная архитектура - разделяет программу на три компонента:
ядро - содержит бизнеслогику приложения
порты - интерфейс взаимодействия внешних систем с ядром
адаптеры - реализации портов
 */

package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("main run");
        SpringApplication.run(SortStrategies.class, args);
        System.out.println("main stop");
    }
}
