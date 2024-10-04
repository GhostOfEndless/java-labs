package org.example;

import org.example.controller.TestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;


/**
 * Главный класс приложения, которое тестирует различные типы списков с использованием {@link TestController}.
 * Выполняет тестирование работы с разными объемами данных и выводит результаты замеров времени.
 */
public class UniversityApplication {

    /**
     * Точка входа в программу. Тестирует работу списков с различным количеством элементов
     * и выводит результаты выполнения операций.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        var testController = new TestController();

        System.out.printf("Start program: %s\n", LocalDateTime.now().format(formatter));

        IntStream.range(1, 6).forEach(i -> {
            var numOfElements = (int) Math.pow(10, i);
            System.out.printf("\n---------- TEST WITH %d ELEMENTS----------\n", numOfElements);
            testController.testArrayList(numOfElements);
            testController.testLinkedList(numOfElements);
            testController.testCustomArrayList(numOfElements);
        });

        System.out.printf("\nFinish program: %s\n", LocalDateTime.now().format(formatter));
    }
}