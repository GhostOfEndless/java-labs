package org.example.service;

import org.example.controller.UniversityController;
import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.entity.Subject;
import org.example.entity.Teacher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDatabase {

    private static final String DB_FILE = "university.txt";
    private final UniversityController controller;

    public FileDatabase(UniversityController controller) {
        this.controller = controller;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DB_FILE))) {
            writer.println("SUBJECTS");
            for (Subject subject : controller.getSubjects()) {
                writer.println(subject.name());
            }

            writer.println("TEACHERS");
            for (Teacher teacher : controller.getTeachers()) {
                writer.printf("%s;%d;%c;%s%n",
                        teacher.getName(),
                        teacher.getAge(),
                        teacher.getGender(),
                        teacher.getSubject().name());
            }

            writer.println("STUDENTS");
            for (Student student : controller.getStudents()) {
                writer.printf("%s;%d;%c;%.2f;%d%n",
                        student.getName(),
                        student.getAge(),
                        student.getGender(),
                        student.getAverageGrade(),
                        student.getBonus());
            }

            writer.println("PARENTS");
            for (Parent parent : controller.getParents()) {
                writer.printf("%s;%d;%c;%s;%d;%s%n",
                        parent.getName(),
                        parent.getAge(),
                        parent.getGender(),
                        parent.getMood(),
                        parent.getTotalBonusPaid(),
                        String.join(",", parent.getChildrenNames()));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            String section = "";

            controller.clearAll();

            while ((line = reader.readLine()) != null) {
                if (line.equals("SUBJECTS") || line.equals("TEACHERS") ||
                        line.equals("STUDENTS") || line.equals("PARENTS")) {
                    section = line;
                    continue;
                }

                switch (section) {
                    case "SUBJECTS":
                        controller.addSubject(new Subject(line));
                        break;
                    case "TEACHERS":
                        loadTeacher(line);
                        break;
                    case "STUDENTS":
                        loadStudent(line);
                        break;
                    case "PARENTS":
                        loadParent(line);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении данных: " + e.getMessage());
        }
    }

    private void loadTeacher(String line) {
        String[] parts = line.split(";");
        Subject subject = controller.findSubjectByName(parts[3]);
        Teacher teacher = new Teacher(parts[0], Integer.parseInt(parts[1]),
                parts[2].charAt(0), subject);
        controller.addTeacher(teacher);
    }

    private void loadStudent(String line) {
        String[] parts = line.split(";");
        Student student = new Student(parts[0], Integer.parseInt(parts[1]),
                parts[2].charAt(0));
        student.setBonus(Integer.parseInt(parts[4]));
        controller.addStudent(student);
    }

    private void loadParent(String line) {
        String[] parts = line.split(";");
        Parent parent = new Parent(parts[0], Integer.parseInt(parts[1]),
                parts[2].charAt(0));
        parent.setMood(parts[3]);
        parent.setTotalBonusPaid(Integer.parseInt(parts[4]));
        String[] childrenNames = parts[5].split(",");
        for (String childName : childrenNames) {
            Student child = controller.findStudentByName(childName);
            if (child != null) {
                parent.addChild(child);
            }
        }
        controller.addParent(parent);
    }
}
