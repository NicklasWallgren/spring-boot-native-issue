package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class ValidationErrorException extends DomainException {

    private final List<ValidationError> validationErrors;

    private ValidationErrorException(final List<ValidationError> validationErrors) {
        super(messageOf(validationErrors));
        this.validationErrors = validationErrors;

        validateSelf();
    }

    public List<ValidationError> toValidationErrors() {
        return Collections.unmodifiableList(validationErrors);
    }

    private void validateSelf() {
//        Validate.notEmpty(validationErrors, "must provide at least on validation error");
    }

    public static ValidationErrorException of(final ValidationError validationError) {
        return ValidationErrorException.of(
            Collections.singletonList(validationError)
        );
    }

    public static ValidationErrorException of(final List<ValidationError> validationErrors) {
        return new ValidationErrorException(validationErrors);
    }

    private static String messageOf(final List<ValidationError> validationErrors) {
        return validationErrors.stream()
            .map(ValidationError::message)
            .collect(Collectors.joining(","));
    }

}
