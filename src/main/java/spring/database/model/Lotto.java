package spring.database.model;

import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.impl.LottoType;

import java.util.Optional;
import java.util.Set;

public interface Lotto {

    void generate();

    LottoType getLottoType();

    Set<Integer> generate(LottoType lottoType);
    Integer validate(Set<Integer> setOfTips ,Set<Integer> resultSet) throws InvalidLottoNumberCountException, InvalidLottoTipException;
}
