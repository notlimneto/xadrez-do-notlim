package xadrezdonotlim.enumeration;

import lombok.Getter;

@Getter
public enum ColorEnum {
    WHITE('w'),
    BLACK('b');

    private final char code;

    ColorEnum(char code) {
        this.code = code;
    }
}