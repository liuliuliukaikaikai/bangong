package org.hdxy.bangong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
    public static void main(String[] args) {

    }

    @Override
    public void run(String... args) throws Exception {



        Runtime.getRuntime().addShutdownHook(new Thread(){

        });

    }
}
