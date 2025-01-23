package dev.mysklad.exception;

public class DataIsIncorrect extends RuntimeException {
    public DataIsIncorrect(String message) {
        super(message);
    }
}
