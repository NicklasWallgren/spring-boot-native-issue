package com.example.demo;

import java.util.Objects;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

//@Converter(autoApply = true)
public final class EmailConverter implements AttributeConverter<Email, String> {

    public EmailConverter() {
    }

    @Override
    public String convertToDatabaseColumn(final Email attribute) {
        Objects.requireNonNull(attribute);

        return attribute.getValue();
    }

    @Override
    public Email convertToEntityAttribute(final String dbData) {
        Objects.requireNonNull(dbData);

        return new Email(dbData);
    }

}
