package spring.database.config.impl;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.database.config.LottoTypeConsumer;
import spring.database.model.impl.LottoType;

@NoArgsConstructor
class LottoTypeConsumerAndProviderImpl implements hu.nyirszikszi.lotto.config.LottoTypeProvider, LottoTypeConsumer {
    private @Getter
    @Setter
    LottoType lottoType;


}
