import com.me.dao.implement.ItemDao;
import com.me.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ItemDaoTest {
    private ItemDao itemDao;
    @Before
    public void setup(){
        itemDao = ItemDao.getInstance();
    }

    @Test
    public void testFindAll() {
        List<Item> itemList = itemDao.findAll();
        for(Item item : itemList){
            System.out.println(item);
        }
    }

}
