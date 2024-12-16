package xadrezdonotlim.enumeration;

import lombok.Getter;

@Getter
public enum ColorEnum {
    WHITE('w'),
    BLACK('b');

    private final char value;

    ColorEnum(char value) {
        this.value = value;
    }
}