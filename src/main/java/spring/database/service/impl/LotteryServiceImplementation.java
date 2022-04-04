package spring.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.config.LottoTypeProvider;
import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.Lotto;
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
    private LottoTypeProvider lottoTypeProvider;

    @Autowired
    public void setLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    @PostConstruct
    void initialize(){
        this.tipMap = new HashMap<>();
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
    public void showResults() {

        this.winNumbers.orElseGet(new Supplier<Set<Integer>>() {
            @Override
            public Set<Integer> get() {
                return lotto.generate();
            }
        });
        Set<Integer> currentWinNumbers = this.winNumbers.orElseGet(() -> lotto.generate());
        StringJoiner result = new StringJoiner(",");
        for (Integer i : currentWinNumbers){
            result.add(String.valueOf(i));
        }
        System.out.println(MessageFormat.format("Az eheti nyerőszámok: {0}", result));
        for(String serial : tipMap.keySet()){ // Map-ban felsorolt kulcsok halmaza
            Set<Integer> tip = tipMap.get(serial);
            try {
                this.lotto.validate(tip, currentWinNumbers);
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
}
