package org.example.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Представляет родителя в системе университета.
 * Расширяет класс Person, добавляя функциональность для работы с детьми-студентами,
 * настроением и выплатой премиальных.
 */
public class Parent extends Person {
    /** Список детей-студентов */
    private final List<Student> children;

    /** Текущее настроение родителя */
    private String mood;

    /** Общая сумма выплаченных премиальных */
    private int totalBonusPaid;

    /**
     * Создает нового родителя с заданными параметрами.
     *
     * @param name   Имя родителя
     * @param age    Возраст родителя
     * @param gender Пол родителя (М - мужской, Ж - женский)
     */
    public Parent(String name, int age, char gender) {
        super(name, age, gender);
        this.children = new ArrayList<>();
        this.mood = "нейтральный";
        this.totalBonusPaid = 0;
    }

    /**
     * Добавляет ребенка-студента к списку детей родителя.
     *
     * @param child Студент, являющийся ребенком данного родителя
     */
    public void addChild(Student child) {
        children.add(child);
    }

    /**
     * Обновляет настроение родителя и выплачивает премиальные детям
     * в зависимости от их средних оценок.
     * Настроение и премии определяются следующим образом:
     * - Средняя оценка 4.6-5.0: "радостный", премия 10000 руб.
     * - Средняя оценка 4.0-4.5: "удовлетворенный", премия 5000 руб.
     * - Средняя оценка менее 4.0: "хмурый", премия 0 руб.
     */
    public void updateMoodAndPayBonus() {
        for (Student child : children) {
            double avgGrade = child.getAverageGrade();
            if (avgGrade >= 4.6 && avgGrade <= 5.0) {
                mood = "радостный";
                int bonus = 10000;
                child.setBonus(bonus);
                totalBonusPaid += bonus;
            } else if (avgGrade >= 4.0 && avgGrade < 4.6) {
                mood = "удовлетворенный";
                int bonus = 5000;
                child.setBonus(bonus);
                totalBonusPaid += bonus;
            } else {
                mood = "хмурый";
                child.setBonus(0);
            }
        }
    }

    /**
     * Возвращает строковое представление родителя.
     *
     * @return Строка с информацией о родителе, включая имя, настроение и общую сумму выплаченных премиальных
     */
    @Override
    public String toString() {
        return String.format("Родитель: %s, Настроение: %s, Выплаченные премиальные: %d руб.",
                name, mood, totalBonusPaid);
    }
}
