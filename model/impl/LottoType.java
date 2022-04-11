package spring.database.model.impl;

import lombok.Getter;

import java.text.MessageFormat;

public enum LottoType {
    Five (1),
    Six (3);
    private final @Getter
    int lessWin;

    LottoType(int lessWin) {
        this.lessWin = lessWin;
    }

    public static LottoType fromInteger(Integer value){
        switch (value){
            case 5 : return LottoType.Five;
            case 6 : return LottoType.Six;
            default: throw new IllegalArgumentException(MessageFormat.format("Nem megfelelő érték: {0}",value));
        }
    }
}
