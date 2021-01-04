package com.lanjing.wallet;

import com.lanjing.wallet.input.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.lanjing.wallet", "com.jester.util.utils", "com.lanjing.wallet.agc",})
@RestController
@MapperScan("com.lanjing.wallet.*")
@ServletComponentScan("com.lanjing.wallet.filter")
@EnableScheduling
public class WalletApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }

    @Autowired
    UserInput userInput;

    @Override
    public void run(String... args) throws Exception {
        //userInput.bb();
    }
}
