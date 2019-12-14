package svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import svc.property.HomeProperties;

/**
 * @author Frankie Yang on 2019-07-09.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApplication2 implements CommandLineRunner {

    @Autowired
    private HomeProperties homeProperties;

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication2.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(homeProperties.toString());
    }

}