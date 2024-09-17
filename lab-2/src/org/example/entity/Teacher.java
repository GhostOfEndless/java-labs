package org.example.entity;

/**
 * Представляет преподавателя в системе университета.
 * Расширяет класс Person, добавляя специфичную для преподавателя информацию
 * и функциональность, такую как преподаваемый предмет и возможность выставлять оценки.
 */
public class Teacher extends Person {
    /** Предмет, который преподает учитель */
    private final Subject subject;

    /**
     * Создает нового преподавателя с заданными параметрами.
     *
     * @param name    Имя преподавателя
     * @param age     Возраст преподавателя
     * @param gender  Пол преподавателя (М - мужской, Ж - женский)
     * @param subject Предмет, который преподает учитель
     */
    public Teacher(String name, int age, char gender, Subject subject) {
        super(name, age, gender);
        this.subject = subject;
    }

    /**
     * Выставляет оценку студенту по предмету, который преподает учитель.
     *
     * @param student Студент, которому выставляется оценка
     * @param grade   Числовое значение оценки
     */
    public void gradeStudent(Student student, int grade) {
        student.addGrade(new Grade(subject, grade));
    }

    /**
     * Возвращает строковое представление преподавателя.
     *
     * @return Строка с информацией о преподавателе, включая имя и преподаваемый предмет
     */
    @Override
    public String toString() {
        return "Преподаватель: %s, Предмет: %s"
                .formatted(name, subject.name());
    }
}

