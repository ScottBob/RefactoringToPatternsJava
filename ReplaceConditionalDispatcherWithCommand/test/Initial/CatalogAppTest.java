package Initial;

import junit.framework.TestCase;
import org.junit.Assert;

public class CatalogAppTest extends TestCase {
    CatalogApp catalogApp = new CatalogApp();

    public void test_new_workshop() {
        HandlerResponse handlerResponse = catalogApp.runTheExecuteActionAndGetResponseFunctionForTesting(CatalogApp.NEW_WORKSHOP);
        String expected = "HandlerResponse{xml=XMLBuilder{xmlString='nullworkshopsworkshop,id=01,name=Workshop{id='01', name='', status=''},status=new,duration=1'}, stylesheet='All workshops stylesheet'}";
        Assert.assertEquals(expected, handlerResponse.toString());
    }
}