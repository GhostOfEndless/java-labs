package org.example;

import javax.swing.SwingUtilities;
import org.example.util.DatabaseManager;
import org.example.util.Logger;
import org.example.util.PropertiesReader;
import org.example.view.MainWindow;

/**
 * Класс {@code Main} является точкой входа в приложение.
 */
public class Main {

  public static void main(String[] args) {
    PropertiesReader propertiesReader = new PropertiesReader("settings.properties");
    Logger logger = new Logger("app.log", propertiesReader);
    DatabaseManager dbManager = new DatabaseManager(logger);
    SwingUtilities.invokeLater(() -> {
      MainWindow mainWindow = new MainWindow(dbManager);
      mainWindow.setVisible(true);
    });
  }
}
