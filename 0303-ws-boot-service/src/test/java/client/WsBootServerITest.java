package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ws.test.server.*;
import org.testng.annotations.Test;
import server.ServerConfiguration;

import java.io.IOException;

@ContextConfiguration(classes = ServerConfiguration.class)
public class WsBootServerITest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testGetUserDetails() throws IOException {
        // GIVEN
        MockWebServiceClient wsClient =
                MockWebServiceClient.createClient(applicationContext);

        RequestCreator requestCreator =
                RequestCreators.withPayload(new ClassPathResource("testRequest.xml"));

        // WHEN
        ResponseActions response = wsClient.sendRequest(requestCreator);

        // THEN
        response.andExpect(ResponseMatchers.noFault()).andExpect(
                ResponseMatchers.payload(new ClassPathResource("testResponse.xml")));
    }
}
