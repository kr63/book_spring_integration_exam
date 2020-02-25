package client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

@Component
public class UserInterceptor implements ClientInterceptor {

    private SimpleLogger simpleLogger;

    @Autowired
    public UserInterceptor(SimpleLogger simpleLogger) {
        super();
        this.simpleLogger = simpleLogger;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        simpleLogger.log(">>>> Client handle request");
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        simpleLogger.log(">>>> Client handle response");
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        simpleLogger.log(">>>> Client handle fault");
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {
        simpleLogger.log(">>>> Client after completion");

    }
}
