package server;

import localhost._10301._0301_ws_xmlconfig_service.UserDetailsResponse;
import localhost._10301._0301_ws_xmlconfig_service.UserRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    @PayloadRoot(
            namespace = "http://localhost:10301/0301-ws-xmlconfig-service",
            localPart = "UserRequest")
    @ResponsePayload
    public UserDetailsResponse getUserDetails(@RequestPayload UserRequest request) {
        UserDetailsResponse response;
        if ("lubos.krnac@gmail.com".equals(request.getEmail())) {
            response = new UserDetailsResponse();
            response.setFirstName("Lubos");
            response.setLastName("Krnac");
        } else {
            throw new IllegalStateException("Simulate error");
        }
        return response;
    }

}
