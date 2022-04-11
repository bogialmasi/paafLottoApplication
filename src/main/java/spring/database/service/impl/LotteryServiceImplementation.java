package spring.database.service.impl;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.config.LottoTypeProvider;
import spring.database.config.impl.LotteryResultEvent;
import spring.database.event.LotteryEvent;
import spring.database.event.LottoTipEvent;
import spring.database.event.ShowResultEvent;
import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.Lotto;
import spring.database.model.impl.LottoType;
import spring.database.service.LotteryService;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Supplier;

@Service
public class LotteryServiceImplementation implements LotteryService {
    private static int SERIAL = 0;
    private Lotto lotto;
    private Map<String, Set<Integer>> tipMap;
    private Optional<Set<Integer>> winNumbers;
    private @Getter
    LottoType lottoType;
    private LottoTypeProvider lottoTypeProvider;
    private EventBus eventBus;

    public LotteryServiceImplementation(@Autowired EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Autowired
    void setEventBus(EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Autowired
    public void setLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    @PostConstruct
    void initialize(){
        this.tipMap = new HashMap<>();
        this.eventBus.register(this);
    }

    @Override
    public void getTips(List<Integer> numbers) {
        Set<Integer> tips = new HashSet<>(numbers);
        tipMap.put(String.valueOf(SERIAL++), tips);
    }

    @Override
    public void lottery() {
        this.winNumbers = Optional.of(this.lotto.generate());
    }

    @Override
    private void showResults() {
        for(String serial : tipMap.keySet()){ // Map-ban felsorolt kulcsok halmaza
            Set<Integer> tip = tipMap.get(serial);
            try {
                Integer result = this.lotto.validate(tip, currentWinNumbers);
                if (result < this.lottoTypeProvider.getLottoType().getLessWin()) {
                    System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvényen nem nyert", serial);
                } else {
                    System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvényen {0}-ese van", serial.result));
                }
            } catch (InvalidLottoTipException e) {
                System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvény érvénytelen", serial));
                // e.printStackTrace();
            } catch (InvalidLottoNumberCountException e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe
    void onLotteryEventHandle(LotteryEvent lotteryEvent){
        this.lotto.generate(lotteryEvent.getType());
    }

    @Subscribe
    void lottoTipEventHandle(LottoTipEvent lottoTipEvent){
        tipMap.put(
                String.valueOf(SERIAL++),
                Collections.unmodifiableSet(LottoTipEvent.getTipSet()));
    }

    void onShowResultEventHandle(ShowResultEvent showResultEvent){
        this.showResults();
    }
}
