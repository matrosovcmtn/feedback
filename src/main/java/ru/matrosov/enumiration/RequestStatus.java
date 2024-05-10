package ru.matrosov.enumiration;

public enum RequestStatus {
    PROCESSING("В обработке"),
    PROCESSED("Обработана");

    private final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
