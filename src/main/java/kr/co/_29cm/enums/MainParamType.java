package kr.co._29cm.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum MainRouterType {
    ORDER   ("o","product order"),
    QUIT    ("q","quit p");

    private final String code;
    private final String message;

    public static MainRouterType from(String value) {

        for (MainRouterType item : MainRouterType.values()) {
            if(Objects.equals(item.getCode(), value))
                return item;
        }

        return null;
    }
}
