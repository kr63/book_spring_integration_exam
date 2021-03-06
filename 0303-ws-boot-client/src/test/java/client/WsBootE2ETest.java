package client;

import net.lkrnac.book.eiws.chapter03.ws.boot.model.UserDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {WsBootClientConfiguration.class})
@DirtiesContext
public class WsBootE2ETest extends AbstractTestNGSpringContextTests {
    private static final int RETRY_TIMEOUT = 20000;

    @Autowired
    private WebServiceClient wsClient;

    // @Test(groups = "maventests")
    @Test
    public void testGetUserDetails() throws IOException, InterruptedException {
        // GIVEN
        Process process = new ProcessExecutor().execute("0303-ws-boot-service.jar");
        try {

            FunctionRetryHandler<String, UserDetailsResponse> retryHandler =
                    new FunctionRetryHandler<>();

            // WHEN
            UserDetailsResponse userDetails =
                    retryHandler.retry(wsClient::getUserDetails, "lubos.krnac@gmail.com",
                            RETRY_TIMEOUT);

            // THEN
            assertEquals(userDetails.getFirstName(), "Lubos");
            assertEquals(userDetails.getLastName(), "Krnac");
        } finally {
            process.destroyForcibly();
            process.waitFor();
        }
    }
}
