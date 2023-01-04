package com.example.demo;

import jakarta.annotation.Nullable;

public abstract class DomainException extends RuntimeException {

    protected DomainException(final String message) {
        this(message, null);
    }

    protected DomainException(final String message, @Nullable final Throwable cause) {
        super(message, cause);
    }

}
