package org.example;

import org.example.controller.MainMenu;
import org.example.controller.UniversityController;

public class UniversityApplication {

    public static void main(String[] args) {
        UniversityController controller = new UniversityController();
        MainMenu menu = new MainMenu(controller);
        menu.show();
    }
}