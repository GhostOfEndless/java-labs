package org.example.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет студента в системе университета.
 * Расширяет класс Person, добавляя функциональность для работы с оценками и премиями.
 */
public class Student extends Person {

    /** Средний балл студента */
    private double averageGrade;

    /** Сумма премиальных студента */
    private int bonus;

    /** Список оценок студента */
    private final List<Grade> grades;

    /**
     * Создает нового студента с заданными параметрами.
     *
     * @param name   Имя студента
     * @param age    Возраст студента
     * @param gender Пол студента (М - мужской, Ж - женский)
     */
    public Student(String name, int age, char gender) {
        super(name, age, gender);
        this.grades = new ArrayList<>();
    }

    /**
     * Добавляет новую оценку студенту и пересчитывает средний балл.
     *
     * @param grade Новая оценка
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
        calculateAverageGrade();
    }

    /**
     * Пересчитывает средний балл студента на основе всех его оценок.
     * Если оценок нет, устанавливает средний балл в 0.
     */
    private void calculateAverageGrade() {
        if (grades.isEmpty()) {
            averageGrade = 0;
        } else {
            double sum = grades.stream().mapToDouble(Grade::value).sum();
            averageGrade = sum / grades.size();
        }
    }

    /**
     * Устанавливает сумму премиальных для студента.
     *
     * @param bonus Сумма премиальных в рублях
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * Возвращает текущий средний балл студента.
     *
     * @return Средний балл
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * Возвращает строковое представление студента.
     *
     * @return Строка с информацией о студенте, включая имя, возраст, пол, средний балл и сумму премиальных
     */
    @Override
    public String toString() {
        return "Студент: %s, Возраст: %d, Пол: %c, Средняя оценка: %.2f, Премиальные: %d руб."
                .formatted(name, age, gender, averageGrade, bonus);
    }
}
