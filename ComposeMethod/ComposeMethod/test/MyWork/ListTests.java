package MyWork;

import org.junit.Assert;
import org.junit.Test;

public class ListTests {

    @Test
    public void can_add_to_list()
    {
        List list = new List();
        list.add("One");
        Assert.assertEquals(1, list.getCount());
        Assert.assertEquals(list.getAt(0), "One");
    }

    @Test
    public void cannot_add_to_read_only_list()
    {
        List list = new List();
        list.setReadOnly(true);
        list.add("One");
        Assert.assertEquals(0, list.getCount());
    }

    @Test
    public void list_at_capacity_grows_after_add()
    {
        List list = new List();
        for (int i = 0; i < 11; ++i) {
            list.add("One");
        }
        Assert.assertEquals(11, list.getCount());
        Assert.assertEquals(list.getAt(10), "One");
    }
}
