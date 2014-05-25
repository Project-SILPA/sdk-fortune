import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import org.silpa.fortune.Fortune;

/**
 * Created by sujith on 25/5/14.
 */
public class TestFortune extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testConstructor() {
        Fortune obj1 = new Fortune(getContext());
        assertNotNull(obj1);

        Fortune obj2 = new Fortune(getContext());
        assertNotNull(obj2);

        Fortune obj3 = new Fortune(getContext());
        assertNotNull(obj3);

        Fortune obj4 = new Fortune(getContext());
        assertNotNull(obj4);
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testFortune() {
        Fortune obj = new Fortune(getContext());
        assertNotNull(obj);

        String quote;

        quote = obj.fortune("love");
        assertTrue(quote == null || quote.contains("love"));
        quote = obj.fortune("happy");
        assertTrue(quote == null || quote.contains("happy"));
        quote = obj.fortune("wealth");
        assertTrue(quote == null || quote.contains("wealth"));
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleName() {
        Fortune obj = new Fortune(getContext());
        assertNotNull(obj);
        assertEquals(Fortune.MODULE_NAME, obj.getModuleName());
    }

    @SmallTest
    @MediumTest
    @LargeTest
    public void testGetModuleInformation() {
        Fortune obj = new Fortune(getContext());
        assertNotNull(obj);
        assertEquals(Fortune.MODULE_INFORMATION, obj.getModuleInformation());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
