package svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Frankie Yang on 2019-07-09.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication3 {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication3.class, args);
    }
}