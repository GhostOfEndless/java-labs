package org.example;

import org.example.controller.RaceController;
import org.example.model.RaceModel;
import org.example.view.RaceView;

import javax.swing.*;

/**
 * Главный класс приложения для запуска гонок кнопок.
 * Демонстрирует использование многопоточности.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RaceModel model = new RaceModel(5, 700); // 5 кнопок, трек шириной 700
            RaceView view = new RaceView(model);
            RaceController controller = new RaceController(model, view);

            view.setController(controller);
            view.setVisible(true);

            // Автоматический старт первой гонки
            controller.startRace();
        });
    }
}