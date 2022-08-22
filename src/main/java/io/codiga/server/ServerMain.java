package io.codiga.server;


import io.codiga.analyzer.AnalyzerFuturePool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerMain {
    public static void main(String... args) {
        AnalyzerFuturePool.getInstance().initialize();
        System.out.println("Do startup logic here");

        SpringApplication.run(ServerMain.class, args);
    }

}
