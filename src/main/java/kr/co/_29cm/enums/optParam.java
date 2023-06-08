package kr.co._29cm.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum optParam {
    ORDER   ('o',"product order"),
    QUIT    ('q',"quit p");

    private final char code;
    private final String message;

}
