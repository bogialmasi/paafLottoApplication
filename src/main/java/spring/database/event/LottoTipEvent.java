package spring.database.event;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LottoTipEvent {

    private @Getter
    Set<Integer> tipSet;

    public LottoTipEvent(Integer... tips) {
        this.tipSet = new HashSet<>(Arrays.asList(tips));
    }
}
