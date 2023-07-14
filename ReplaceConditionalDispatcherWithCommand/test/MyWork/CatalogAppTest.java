package MyWork;

import junit.framework.TestCase;
import org.junit.Assert;

public class CatalogAppTest extends TestCase {
    CatalogApp catalogApp = new CatalogApp();

    public void test_new_workshop() {
        HandlerResponse handlerResponse = catalogApp.runTheExecuteActionAndGetResponseFunctionForTesting(CatalogApp.NEW_WORKSHOP);
        String expected = "HandlerResponse{xml=WorkshopManager{workshopRepository=WorkshopRespository{Workshop{id='01', name='Workshop{id='01', name='', status=''}', status='new'}}}, stylesheet='General Style'}";
        Assert.assertEquals(expected, handlerResponse.toString());
    }
}