package com.example.demo;

import java.util.Objects;

import jakarta.persistence.AttributeConverter;

public final class EmailConverter implements AttributeConverter<Email, String> {

    public EmailConverter() {
    }

    @Override
    public String convertToDatabaseColumn(final Email attribute) {
        Objects.requireNonNull(attribute);

        return attribute.toValueOrEmpty();
    }

    @Override
    public Email convertToEntityAttribute(final String dbData) {
        Objects.requireNonNull(dbData);

        return Email.of(dbData);
    }

}
