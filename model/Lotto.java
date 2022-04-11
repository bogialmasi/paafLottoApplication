package spring.database.model;

import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;

import java.util.Optional;
import java.util.Set;

public interface Lotto {

    Set<Integer> generate();
    Integer validate(Set<Integer> setOfTips ,Set<Integer> resultSet) throws InvalidLottoNumberCountException, InvalidLottoTipException;
}
