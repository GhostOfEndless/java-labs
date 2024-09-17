package org.example.view;

import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.entity.Teacher;

import java.util.List;

/**
 * Класс, отвечающий за отображение результатов симуляции университетской системы.
 * Предоставляет методы для вывода информации о преподавателях, студентах и родителях.
 */
public class UniversityView {

    /**
     * Отображает результаты симуляции обучения в университете.
     * Выводит информацию о преподавателях, студентах и родителях в консоль.
     *
     * @param students Список студентов для отображения
     * @param teachers Список преподавателей для отображения
     * @param parents  Список родителей для отображения
     */
    public void displayResults(List<Student> students, List<Teacher> teachers, List<Parent> parents) {
        System.out.println("Результаты симуляции обучения:");

        System.out.println("\nПреподаватели:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }

        System.out.println("\nСтуденты:");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("\nРодители:");
        for (Parent parent : parents) {
            System.out.println(parent);
        }
    }
}