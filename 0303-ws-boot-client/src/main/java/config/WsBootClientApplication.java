package config;

import client.WebServiceClient;
import client.WsBootClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@Import(WsBootClientConfiguration.class)
public class WsBootClientApplication {

    @Autowired
    private WebServiceClient wsClient;

    public static void main(String[] args) {
        SpringApplication.run(WsBootClientApplication.class, args);
    }

    @PostConstruct
    public void test() {
        UserDetailsResponse userDetails =
                wsClient.getUserDetails("lubos.krnac@gmail.com");
        log.info("User Details: " + userDetails.getFirstName() + " "
                + userDetails.getLastName());
    }
}
