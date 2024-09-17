package org.example.controller;

import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Контроллер университета, управляющий взаимодействием между студентами, преподавателями и родителями.
 * Этот класс отвечает за инициализацию данных, симуляцию выставления оценок и обновление настроения родителей.
 */
public class UniversityController {
    /** Список студентов университета */
    private final List<Student> students;
    /** Список преподавателей университета */
    private final List<Teacher> teachers;
    /** Список родителей студентов */
    private final List<Parent> parents;
    /** Список предметов, преподаваемых в университете */
    private final List<Subject> subjects;

    /**
     * Конструктор класса UniversityController.
     * Инициализирует списки студентов, преподавателей, родителей и предметов,
     * а затем вызывает метод для заполнения их начальными данными.
     */
    public UniversityController() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        parents = new ArrayList<>();
        subjects = new ArrayList<>();
        initializeData();
    }

    /**
     * Инициализирует начальные данные университета.
     * Создает предметы, преподавателей, студентов и их родителей.
     */
    private void initializeData() {
        subjects.add(new Subject("Математика"));
        subjects.add(new Subject("Физика"));
        subjects.add(new Subject("Информатика"));

        teachers.add(new Teacher("Иванов И.И.", 45, 'М', subjects.get(0)));
        teachers.add(new Teacher("Петрова П.П.", 40, 'Ж', subjects.get(1)));
        teachers.add(new Teacher("Сидоров С.С.", 50, 'М', subjects.get(2)));

        Random random = new Random();

        for (int i = 1; i <= 5; i++) {
            Student student = new Student("Студент " + i, random.nextInt(4) + 18, 'М');
            students.add(student);

            Parent parent = new Parent("Родитель " + i, random.nextInt(20) + 40, 'Ж');
            parent.addChild(student);
            parents.add(parent);
        }
    }

    /**
     * Симулирует процесс выставления оценок.
     * Каждый преподаватель выставляет по 5 случайных оценок (от 3 до 5) каждому студенту.
     */
    public void simulateGrading() {
        Random random = new Random();
        for (Teacher teacher : teachers) {
            for (Student student : students) {
                for (int i = 0; i < 5; i++) {
                    int grade = random.nextInt(3) + 3; // Оценки от 3 до 5
                    teacher.gradeStudent(student, grade);
                }
            }
        }
    }

    /**
     * Обновляет настроение всех родителей на основе успеваемости их детей.
     */
    public void updateParentsMood() {
        for (Parent parent : parents) {
            parent.updateMoodAndPayBonus();
        }
    }

    /**
     * Возвращает список всех студентов университета.
     *
     * @return Список студентов
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Возвращает список всех преподавателей университета.
     *
     * @return Список преподавателей
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Возвращает список всех родителей студентов.
     *
     * @return Список родителей
     */
    public List<Parent> getParents() {
        return parents;
    }
}