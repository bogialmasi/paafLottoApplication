package spring.database.service;

import java.util.List;

public interface LotteryService {
    void getTips(List<Integer> numbers);
    /* void lottery(); a lottósorsolás mostmár nem függvény hanem egy esemény generálja*/
    void showResults();
}
