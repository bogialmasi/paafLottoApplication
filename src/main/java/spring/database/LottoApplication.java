package spring.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import spring.database.config.LottoTypeConsumer;
import spring.database.model.impl.LottoType;
import spring.database.service.LotteryService;
import spring.database.service.impl.LotteryServiceImplementation;

import java.text.MessageFormat;
import java.util.Arrays;

@SpringBootApplication
public class LottoApplication {

	private static LottoType getLottoType(String arg) throws NumberFormatException{
		Integer value = Integer.valueOf(arg);
		return LottoType.fromInteger(value);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LottoApplication.class, args);
		LottoTypeConsumer lottoTypeConsumer = context.getBean(LottoTypeConsumer.class);
		if(args.length!=0) {
			lottoTypeConsumer.setLottoType(getLottoType(args[0]));
		} else lottoTypeConsumer.setLottoType(LottoType.Five);
		LotteryService lotteryService = context.getBean(LotteryService.class);
		lotteryService.getTips(Arrays.asList(3, 4, 5, 6, 7));
		lotteryService.getTips(Arrays.asList(3, 4, 5, 6, 7, 8));
	}
}
