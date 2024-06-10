package com.karacamehmet.karacablog.core.message.constant;

public class Messages {
    public static class BusinessErrors {
        public static final String NO_USER_FOUND = "noUserFound";
        public static final String USER_ALREADY_EXISTS = "userAlreadyExists";
        public static final String WRONG_USERNAME_OR_PASSWORD = "wrongUsernameOrPassword";
        public static final String NO_POST_FOUND = "noPostFound";
    }

    public static class ValidationErrors {
        public static final String NOT_BLANK = "{validation.notBlank}";
        public static final String NOT_NULL = "{validation.notNull}";
        public static final String NOT_EMPTY = "{validation.notEmpty}";
        public static final String MIN = "{validation.minValue}";
        public static final String EMAIL = "{validation.email}";
        public static final String LENGTH = "{validation.length}";
    }
}
