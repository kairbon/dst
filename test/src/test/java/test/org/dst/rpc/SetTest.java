package test.org.dst.rpc;

import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.dst.server.generated.SetProtocol;
import org.dst.server.service.DstSetService;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SetTest {

    @Test
    public void testSetRpcCall() {

        ProxyOnClient<DstSetService> setProxy = new ProxyOnClient(DstSetService.class);
        DstSetService setService = setProxy.openConnection();

        // Test set put.
        SetProtocol.SetPutRequest.Builder setPutRequestBuilder =
                SetProtocol.SetPutRequest.newBuilder();
        setPutRequestBuilder.setKey("k1");
        final List<String> values = ImmutableList.of("v1", "v2", "v3","v1");
        values.forEach(value -> setPutRequestBuilder.addValues(value));

        SetProtocol.SetPutResponse setPutResponse =
                setService.setPut(setPutRequestBuilder.build());
        Assert.assertEquals("ok", setPutResponse.getStatus());

        // Test set get.
        SetProtocol.SetGetRequest.Builder setGetRequestBuilder =
                SetProtocol.SetGetRequest.newBuilder();
        setGetRequestBuilder.setKey("k1");

        SetProtocol.SetGetResponse setGetResponse =
                setService.setGet(setGetRequestBuilder.build());

        final List<String> results = ImmutableList.of("v1", "v2", "v3");
        Assert.assertEquals("ok", setGetResponse.getStatus());
        Assert.assertEquals(results, setGetResponse.getValuesList());

        setProxy.closeConnection();
    }
}
