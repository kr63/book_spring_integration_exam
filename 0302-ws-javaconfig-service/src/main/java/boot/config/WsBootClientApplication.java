package boot.config;

import boot.client.WebServiceClient;
import boot.client.WsBootClientConfiguration;
import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Configuration
@Import(WsBootClientConfiguration.class)
public class WsBootClientApplication {

    private static final Logger log = LoggerFactory.getLogger(WebServiceClient.class);
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
