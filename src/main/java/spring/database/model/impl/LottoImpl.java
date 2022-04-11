package spring.database.model.impl;

import com.google.common.eventbus.EventBus;
import spring.database.config.LottoTypeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.database.config.impl.LotteryResultEvent;
import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.Lotto;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Component
class LottoImpl implements Lotto {
    private Random random;
    private LottoTypeProvider lottoTypeProvider;
    private LottoType lottoType;
    private Set<Integer> currentLottery;
    private EventBus eventBus;

    /*
    @Autowired
    void setLottoTypeProvider(LottoTypeProvider lottoTypeProvider){
        this.lottoTypeProvider = lottoTypeProvider;
    } nincs már erre szükség */

    public LottoImpl(Random random) {
        this.random = random;
        this.eventBus = new EventBus();
    }

    public LottoType getLottoType() {
        return lottoType;
    }

    @PostConstruct
    void initialize(){
        this.random = new Random();
        System.out.println("Lotto létrejött");
    }

    @Autowired
    void setEventBus(EventBus eventBus){

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
        return !values.contains(tip);
    }

    @Override
    public void generate(){
        this.currentLottery = (this.getLottoType().equals(LottoType.Five))
                ? generateNumbers(90, 5)
                : generateNumbers(45,6);
    }

    @Override
    void generate(LottoType lottoType){
        this.lottoType = lottoType;
        this.generate();
        this.eventBus.post(new LotteryResultEvent(this.currentLottery.toArray(currentLottery.toArray(new Integer[]{}))));
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
