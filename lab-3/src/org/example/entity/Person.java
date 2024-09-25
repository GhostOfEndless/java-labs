package org.example.entity;

/**
 * Абстрактный класс, представляющий человека в системе университета.
 * Базовый класс для классов Студент, Родитель и Преподаватель
 */
public abstract class Person {

    /** Имя человека */
    protected String name;

    /** Возраст человека */
    protected int age;

    /** Пол человека (М - мужской, Ж - женский) */
    protected char gender;

    /**
     * Конструктор для создания нового объекта Person.
     *
     * @param name   Имя человека
     * @param age    Возраст человека
     * @param gender Пол человека (М - мужской, Ж - женский)
     */
    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Возвращает строковое представление объекта Person
     * Этот метод должен быть реализован в подклассах для предоставления
     * специфичной для каждого типа человека информации
     *
     * @return Строковое представление объекта
     */
    @Override
    public abstract String toString();
}
