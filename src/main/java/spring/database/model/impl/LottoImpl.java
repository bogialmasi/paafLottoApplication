package spring.database.model.impl;

import hu.nyirszikszi.lotto.config.LottoTypeProvider;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.Lotto;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Lazy
@Component
@NoArgsConstructor
class LottoImpl implements Lotto {
    private Random random;
    private LottoTypeProvider lottoTypeProvider;

    @Autowired
    void setLottoTypeProvider(LottoTypeProvider lottoTypeProvider){
        this.lottoTypeProvider = lottoTypeProvider;
    }

    @PostConstruct
    void initialize(){
        this.random = new Random();
        System.out.println("Lotto létrejött");
    }

    private Set<Integer> generateNumbers(int maxValue, int count){
        Set<Integer> result = new HashSet<>();
        while(result.size()<count){
            result.add(this.random.nextInt(maxValue)+1);
        }
        return result;
    }

    private boolean isValid(Integer tip){
        return (this.lottoTypeProvider.getLottoType() == LottoType.Five)
                ? tip <=90
                : tip <= 45;
    }

    private boolean isWrongTip(Integer tip, Set<Integer> values){
        return values.contains(tip);
    }

    @Override
    public Set<Integer> generate(){
        return(lottoTypeProvider.equals(LottoType.Five))
            ? generateNumbers(90,5)
            : generateNumbers(45,6);
    }

    @Override
    public Integer validate(Set<Integer> setOfTips, Set<Integer> resultSet) throws InvalidLottoNumberCountException, InvalidLottoTipException {
        int result=0;
        if(setOfTips.size() != resultSet.size()) throw new InvalidLottoNumberCountException();
        for(Integer tip : setOfTips){
            if(!isValid(tip)) throw new InvalidLottoTipException();
            if(!isWrongTip(tip,resultSet)) result ++;
        }
        return result;
    }

}
