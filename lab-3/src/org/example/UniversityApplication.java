package org.example;

import org.example.controller.UniversityController;
import org.example.view.UniversityView;

/**
 * Класс UniversityApplication представляет собой точку входа в приложение,
 * которое симулирует процесс выставления оценок студентам и обновление настроения их родителей.
 */
public class UniversityApplication {

    /**
     * Основной метод, который запускает приложение.
     * Он создает экземпляры контроллера университета и представления, а затем:
     * 1. Симулирует процесс выставления оценок студентам.
     * 2. Обновляет настроение родителей на основе оценок студентов.
     * 3. Отображает результаты для студентов, преподавателей и родителей.
     *
     * @param args аргументы командной строки (не используются в данном приложении).
     */
    public static void main(String[] args) {
        UniversityController controller = new UniversityController();
        UniversityView view = new UniversityView();

        // Симуляция выставления оценок
        controller.simulateGrading();

        // Обновление настроения родителей
        controller.updateParentsMood();

        // Отображение результатов
        view.displayResults(controller.getStudents(), controller.getTeachers(), controller.getParents());
    }
}