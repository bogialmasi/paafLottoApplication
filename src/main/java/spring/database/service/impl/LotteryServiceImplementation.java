package spring.database.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.exception.InvalidLottoNumberCountException;
import spring.database.exception.InvalidLottoTipException;
import spring.database.model.Lotto;
import spring.database.service.LotteryService;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.*;

@Service
public class LotteryServiceImplementation implements LotteryService {
    private static int SERIAL = 0;
    private Lotto lotto;
    private Map<String, Set<Integer>> tipMap;
    private Set<Integer> winNumbers;

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
        this.winNumbers = this.lotto.generate();
    }

    @Override
    public void showResults() {
        for(String serial : tipMap.keySet()){ // Map-ban felsorolt kulcsok halmaza
            Set<Integer> tip = tipMap.get(serial);
            try {
                this.lotto.validate(tip, this.winNumbers);
            } catch (InvalidLottoNumberCountException e) {
                if (result < this.lottoTypeProvider.getLottoType().getLessWin()) {
                    System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvényen nem nyert", serial);
                }else{
                    System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvényen {0}-ese van", serial.result));
                }
                e.printStackTrace();
            } catch (InvalidLottoTipException e) {
                System.out.println(MessageFormat.format(" A {0} sorszámú lottószelvény érvénytelen", serial));
                // e.printStackTrace();
            }
        }
    }
}
