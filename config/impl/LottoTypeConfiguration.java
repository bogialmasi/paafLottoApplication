package spring.database.config.impl;

import hu.nyirszikszi.lotto.config.LottoTypeProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.database.config.impl.LottoTypeConsumerAndProviderImpl;

@Configuration
class LottoTypeConfiguration {
    @Bean
    LottoTypeProvider lottoTypeProvider(){
        return new LottoTypeConsumerAndProviderImpl();
    }
}
