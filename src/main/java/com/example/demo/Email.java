package com.example.demo;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.internal.util.DomainNameUtil;

public final class Email extends ValueObject<String> {
    private static final int MAX_LOCAL_PART_LENGTH = 64;
    @SuppressWarnings("AvoidEscapedUnicodeCharacters")
    private static final String LOCAL_PART_ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~\u0080-\uFFFF-]";
    @SuppressWarnings("AvoidEscapedUnicodeCharacters")
    private static final String LOCAL_PART_INSIDE_QUOTES_ATOM = "(?:[a-z0-9!#$%&'*.(),<>\\[\\]:;  @+/=?^_`{|}~\u0080-\uFFFF-]|\\\\\\\\|\\\\\\\")";
    private static final Pattern LOCAL_PART_PATTERN = Pattern.compile(
        "(?:" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" +
            "(?:\\." + "(?:" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" + ")*", CASE_INSENSITIVE
    );

    private Email() {
        super();
    }

    private Email(final String value) {
        super(value);
    }

    public String toValueOrEmpty() {
        if (value != null) {
            return value;
        }

        return "";
    }

    @Override
    protected void validateSelf() {
        if (!isValidEmail()) {
            throw ValidationErrorException.of(ValidationError.of("invalid email address", value, "email"));
        }
    }

    /**
     * Checks whether the email is valid or not.
     * <p>
     * The implementation details originates from {@link org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator}
     */
    private boolean isValidEmail() {
        if (value == null) {
            return false;
        }

        final int splitPosition = value.lastIndexOf('@');
        if (splitPosition < 0) {
            return false;
        }

        final String localPart = value.substring(0, splitPosition);
        final String domainPart = value.substring(splitPosition + 1);

        return isValidEmailLocalPart(localPart) && DomainNameUtil.isValidEmailDomainAddress(domainPart);
    }

    public static Email of(final String value) {
        return new Email(value);
    }

    private static boolean isValidEmailLocalPart(final String localPart) {
        if (localPart.length() > MAX_LOCAL_PART_LENGTH) {
            return false;
        }
        final Matcher matcher = LOCAL_PART_PATTERN.matcher(localPart);
        return matcher.matches();
    }

}
