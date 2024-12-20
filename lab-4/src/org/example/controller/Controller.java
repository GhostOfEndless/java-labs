package org.example.controller;

import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.util.Logger;

/**
 * Класс {@code Controller} управляет взаимодействием между студентами,
 * преподавателями и родителями. Он обеспечивает процесс выставления
 * оценок студентам и обработку возможных исключений.
 */
public class Controller {

  private final Logger logger;

  public Controller(Logger logger){
    this.logger = logger;
  }

  /**
   * Выставляет оценки студенту от конкретного преподавателя и обрабатывает
   * возможные исключения, такие как неправильное количество оценок.
   *
   * @param teacher преподаватель, выставляющий оценки
   * @param student студент, получающий оценки
   * @param grades  массив оценок
   */
  public void assignGrades(Teacher teacher, Student student, int[] grades) {
    try {
      teacher.setGrades(student, grades);
    } catch (IllegalArgumentException e) {
      System.out.println("Ошибка: " + e.getMessage());
      logger.logError("Ошибка: " + e.getMessage());
    }
  }
}
