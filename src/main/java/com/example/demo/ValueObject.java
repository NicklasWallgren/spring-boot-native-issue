package com.example.demo;

import java.util.Objects;

import jakarta.annotation.Nullable;

public abstract class ValueObject<T> {

    @Nullable
    protected final T value;

    protected ValueObject() {
        value = null;
    }

    protected ValueObject(final T value) {
        this.value = value;

        validateSelf();
    }

    protected abstract void validateSelf();

    @Nullable
    public T toValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ValueObject<?> that)) {
            return false;
        }
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
