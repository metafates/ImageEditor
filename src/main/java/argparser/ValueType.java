package main.java.argparser;

public enum ValueType {
    INTEGER(
        "Integer",
        "^-?\\d+$"
    ),
    FLOAT(
        "Float",
        "^-?\\d+(\\.\\d*)?$"
    ),
    STRING(
        "String",
        "^.+$"
    ),
    NONE(
        "None",
        "^$"
    );

    private final String typeName;
    private final String regexPattern;

    ValueType(final String typeName, final String regexPattern) {
        this.typeName = typeName;
        this.regexPattern = regexPattern;
    }

    public boolean validate(final String value) {
        return value.matches(regexPattern);
    }

    public String getTypeName() {
        return typeName;
    }
}
