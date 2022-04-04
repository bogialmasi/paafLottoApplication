package spring.database.service;

import java.util.List;

public interface LotteryService {
    void getTips(List<Integer> numbers);
    void lottery();
    void showResults();
}
