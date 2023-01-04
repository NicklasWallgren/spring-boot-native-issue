package com.example.demo;

import jakarta.annotation.Nullable;

public record ValidationError(String message, @Nullable Object rejectValue, @Nullable String field) {

    public static ValidationError of(final String message) {
        return new ValidationError(message, null, null);
    }

    public static ValidationError of(final String message, @Nullable final Object rejectedValue) {
        return new ValidationError(message, rejectedValue, null);
    }

    public static ValidationError of(final String message, @Nullable final Object rejectedValue, final String field) {
        return new ValidationError(message, rejectedValue, field);
    }

}
