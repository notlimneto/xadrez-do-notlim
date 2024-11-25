package xadrezdonotlim.enumeration;

import lombok.Getter;

@Getter
public enum PositionIdentifiersEnum {
    ROWS("12345678"),
    COLUMNS("abcdefgh");

    private final String values;

    PositionIdentifiersEnum(String values) {
        this.values = values;
    }
}
