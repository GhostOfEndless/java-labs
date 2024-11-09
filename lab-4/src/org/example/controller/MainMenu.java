package org.example.controller;

import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.entity.Teacher;
import org.example.service.FileDatabase;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final UniversityController controller;
    private final FileDatabase database;
    private final Scanner scanner;

    public MainMenu(UniversityController controller) {
        this.controller = controller;
        this.database = new FileDatabase(controller);
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Меню управления университетом ===");
            System.out.println("1. Сохранить данные в файл");
            System.out.println("2. Загрузить данные из файла");
            System.out.println("3. Добавить новые данные");
            System.out.println("4. Изменить существующие данные");
            System.out.println("5. Удалить данные");
            System.out.println("6. Показать все данные");
            System.out.println("0. Выход");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    database.saveToFile();
                    System.out.println("Данные успешно сохранены");
                    break;
                case 2:
                    database.loadFromFile();
                    System.out.println("Данные успешно загружены");
                    break;
                case 3:
                    showAddMenu();
                    break;
                case 4:
                    showUpdateMenu();
                    break;
                case 5:
                    showDeleteMenu();
                    break;
                case 6:
                    showAllData();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void showAllData() {
        System.out.println("\n=== Все данные ===");
        System.out.println("\nПредметы:");
        controller.getSubjects().forEach(System.out::println);

        System.out.println("\nПреподаватели:");
        controller.getTeachers().forEach(System.out::println);

        System.out.println("\nСтуденты:");
        controller.getStudents().forEach(System.out::println);

        System.out.println("\nРодители:");
        controller.getParents().forEach(System.out::println);
    }

    private void showAddMenu() {
        System.out.println("\n=== Добавление новых данных ===");
        System.out.println("1. Добавить студента");
        System.out.println("2. Добавить преподавателя");
        System.out.println("3. Добавить родителя");
        System.out.println("4. Добавить предмет");
        System.out.println("0. Назад");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                addTeacher();
                break;
            case 3:
                addParent();
                break;
            case 4:
                addSubject();
                break;
        }
    }

    // Методы для добавления данных
    private void addStudent() {
        System.out.println("Введите имя студента:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите пол (М/Ж):");
        char gender = scanner.nextLine().charAt(0);

        Student student = new Student(name, age, gender);
        controller.addStudent(student);
        System.out.println("Студент успешно добавлен");
    }

    private void addTeacher() {
        System.out.println("Введите имя преподавателя:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите пол (М/Ж):");
        char gender = scanner.nextLine().charAt(0);

        // Показать доступные предметы
        System.out.println("Доступные предметы:");
        List<Subject> subjects = controller.getSubjects();
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, subjects.get(i).name());
        }

        System.out.println("Выберите номер предмета:");
        int subjectIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
            Teacher teacher = new Teacher(name, age, gender, subjects.get(subjectIndex));
            controller.addTeacher(teacher);
            System.out.println("Преподаватель успешно добавлен");
        } else {
            System.out.println("Неверный номер предмета");
        }
    }

    private void addParent() {
        System.out.println("Введите имя родителя:");
        String name = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите пол (М/Ж):");
        char gender = scanner.nextLine().charAt(0);

        Parent parent = new Parent(name, age, gender);

        // Показать доступных студентов
        System.out.println("Доступные студенты:");
        List<Student> students = controller.getStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, students.get(i).getName());
        }

        System.out.println("Введите номера студентов (через запятую):");
        String[] indices = scanner.nextLine().split(",");

        for (String index : indices) {
            int studentIndex = Integer.parseInt(index.trim()) - 1;
            if (studentIndex >= 0 && studentIndex < students.size()) {
                parent.addChild(students.get(studentIndex));
            }
        }

        controller.addParent(parent);
        System.out.println("Родитель успешно добавлен");
    }

    private void addSubject() {
        System.out.println("Введите название предмета:");
        String name = scanner.nextLine();
        Subject subject = new Subject(name);
        controller.addSubject(subject);
        System.out.println("Предмет успешно добавлен");
    }

    private void showUpdateMenu() {
        while (true) {
            System.out.println("\n=== Изменение данных ===");
            System.out.println("1. Изменить данные студента");
            System.out.println("2. Изменить данные преподавателя");
            System.out.println("3. Изменить данные родителя");
            System.out.println("4. Изменить предмет");
            System.out.println("0. Назад");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    updateStudent();
                    break;
                case 2:
                    updateTeacher();
                    break;
                case 3:
                    updateParent();
                    break;
                case 4:
                    updateSubject();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void updateStudent() {
        List<Student> students = controller.getStudents();
        System.out.println("Выберите студента для изменения:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, students.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < students.size()) {
            Student student = students.get(index);

            System.out.println("Введите новое имя (или Enter для пропуска):");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                student.setName(name);
            }

            System.out.println("Введите новый возраст (или 0 для пропуска):");
            int age = Integer.parseInt(scanner.nextLine());
            if (age > 0) {
                student.setAge(age);
            }

            System.out.println("Введите новый пол (М/Ж) (или Enter для пропуска):");
            String gender = scanner.nextLine();
            if (!gender.isEmpty()) {
                student.setGender(gender.charAt(0));
            }

            System.out.println("Студент успешно обновлен");
        } else {
            System.out.println("Неверный номер студента");
        }
    }

    private void updateTeacher() {
        List<Teacher> teachers = controller.getTeachers();
        System.out.println("Выберите преподавателя для изменения:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, teachers.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < teachers.size()) {
            Teacher teacher = teachers.get(index);

            System.out.println("Введите новое имя (или Enter для пропуска):");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                teacher.setName(name);
            }

            System.out.println("Введите новый возраст (или 0 для пропуска):");
            int age = Integer.parseInt(scanner.nextLine());
            if (age > 0) {
                teacher.setAge(age);
            }

            System.out.println("Введите новый пол (М/Ж) (или Enter для пропуска):");
            String gender = scanner.nextLine();
            if (!gender.isEmpty()) {
                teacher.setGender(gender.charAt(0));
            }

            System.out.println("Выбрать новый предмет? (да/нет)");
            if (scanner.nextLine().equalsIgnoreCase("да")) {
                System.out.println("Доступные предметы:");
                List<Subject> subjects = controller.getSubjects();
                for (int i = 0; i < subjects.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, subjects.get(i).name());
                }

                System.out.println("Выберите номер предмета:");
                int subjectIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
                    teacher.setSubject(subjects.get(subjectIndex));
                }
            }

            System.out.println("Преподаватель успешно обновлен");
        } else {
            System.out.println("Неверный номер преподавателя");
        }
    }

    private void updateParent() {
        List<Parent> parents = controller.getParents();
        System.out.println("Выберите родителя для изменения:");
        for (int i = 0; i < parents.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, parents.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < parents.size()) {
            Parent parent = parents.get(index);

            System.out.println("Введите новое имя (или Enter для пропуска):");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                parent.setName(name);
            }

            System.out.println("Введите новый возраст (или 0 для пропуска):");
            int age = Integer.parseInt(scanner.nextLine());
            if (age > 0) {
                parent.setAge(age);
            }

            System.out.println("Введите новый пол (М/Ж) (или Enter для пропуска):");
            String gender = scanner.nextLine();
            if (!gender.isEmpty()) {
                parent.setGender(gender.charAt(0));
            }

            System.out.println("Обновить список детей? (да/нет)");
            if (scanner.nextLine().equalsIgnoreCase("да")) {
                parent.clearChildren();

                System.out.println("Доступные студенты:");
                List<Student> students = controller.getStudents();
                for (int i = 0; i < students.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, students.get(i).getName());
                }

                System.out.println("Введите номера студентов через запятую:");
                String[] indices = scanner.nextLine().split(",");
                for (String idx : indices) {
                    int studentIndex = Integer.parseInt(idx.trim()) - 1;
                    if (studentIndex >= 0 && studentIndex < students.size()) {
                        parent.addChild(students.get(studentIndex));
                    }
                }
            }

            System.out.println("Родитель успешно обновлен");
        } else {
            System.out.println("Неверный номер родителя");
        }
    }

    private void updateSubject() {
        List<Subject> subjects = controller.getSubjects();
        System.out.println("Выберите предмет для изменения:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, subjects.get(i).name());
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < subjects.size()) {
            System.out.println("Введите новое название предмета:");
            String name = scanner.nextLine();
            subjects.set(index, new Subject(name));
            System.out.println("Предмет успешно обновлен");
        } else {
            System.out.println("Неверный номер предмета");
        }
    }

    private void showDeleteMenu() {
        while (true) {
            System.out.println("\n=== Удаление данных ===");
            System.out.println("1. Удалить студента");
            System.out.println("2. Удалить преподавателя");
            System.out.println("3. Удалить родителя");
            System.out.println("4. Удалить предмет");
            System.out.println("0. Назад");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    deleteStudent();
                    break;
                case 2:
                    deleteTeacher();
                    break;
                case 3:
                    deleteParent();
                    break;
                case 4:
                    deleteSubject();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор");
            }
        }
    }

    private void deleteStudent() {
        List<Student> students = controller.getStudents();
        System.out.println("Выберите студента для удаления:");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, students.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < students.size()) {
            Student student = students.get(index);
            controller.removeStudent(student);
            System.out.println("Студент успешно удален");
        } else {
            System.out.println("Неверный номер студента");
        }
    }

    private void deleteTeacher() {
        List<Teacher> teachers = controller.getTeachers();
        System.out.println("Выберите преподавателя для удаления:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, teachers.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < teachers.size()) {
            Teacher teacher = teachers.get(index);
            controller.removeTeacher(teacher);
            System.out.println("Преподаватель успешно удален");
        } else {
            System.out.println("Неверный номер преподавателя");
        }
    }

    private void deleteParent() {
        List<Parent> parents = controller.getParents();
        System.out.println("Выберите родителя для удаления:");
        for (int i = 0; i < parents.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, parents.get(i));
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < parents.size()) {
            Parent parent = parents.get(index);
            controller.removeParent(parent);
            System.out.println("Родитель успешно удален");
        } else {
            System.out.println("Неверный номер родителя");
        }
    }

    private void deleteSubject() {
        List<Subject> subjects = controller.getSubjects();
        System.out.println("Выберите предмет для удаления:");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, subjects.get(i).name());
        }

        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index >= 0 && index < subjects.size()) {
            Subject subject = subjects.get(index);
            if (controller.isSubjectInUse(subject)) {
                System.out.println("Невозможно удалить предмет, так как он используется преподавателями");
                return;
            }
            controller.removeSubject(subject);
            System.out.println("Предмет успешно удален");
        } else {
            System.out.println("Неверный номер предмета");
        }
    }

}
