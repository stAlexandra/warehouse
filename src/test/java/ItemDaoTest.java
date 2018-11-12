import com.me.dao.implement.ItemDao;
import org.junit.Before;
import org.junit.Test;
public class ItemDaoTest {
    private ItemDao itemDao;
    @Before
    public void setup(){
        itemDao = ItemDao.getInstance();
    }

    @Test
    public void testFindAll() {

        itemDao.findAll();

    }

}
