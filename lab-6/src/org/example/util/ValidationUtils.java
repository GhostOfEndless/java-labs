package org.example.util;

/**
 * Утилитный класс для проверки валидности различных данных.
 */
public class ValidationUtils {

  /**
   * Проверяет, является ли имя допустимым.
   * Имя считается валидным, если:
   * - оно не равно {@code null};
   * - оно не является пустой строкой или строкой, состоящей только из пробелов;
   * - оно содержит только буквы (латиница, кириллица) и пробелы.
   *
   * @param name строка, представляющая имя для проверки.
   * @return {@code true}, если имя валидно, иначе {@code false}.
   */
  public static boolean isValidName(String name) {
    return name != null && !name.trim().isEmpty() && name.matches("[a-zA-Zа-яА-я\\s]+");
  }
}