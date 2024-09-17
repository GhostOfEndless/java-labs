package org.example.entity;
/**
 * Представляет оценку студента по конкретному предмету
 * Этот класс является неизменяемым (immutable)
 */
public record Grade(Subject subject, int value) {
}