package test;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import utilities.ZipUtility;

import java.io.File;
import java.util.List;

public class ZipUtilityTest extends TestCase {

    File file = new File("c:/UTP7test/testZip.zip");

    @Before
    public void before() {
        Assert.assertTrue(file.exists());

    }

    public void testSearchByName() throws Exception {
        List<String> files = ZipUtility.searchByName(file, "IT");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    public void testSearchByContent() throws Exception {
        List<String> files = ZipUtility.searchByContent(file, "boat bobbed");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    public void testSearchByNameFail() throws Exception {
        List<String> files = ZipUtility.searchByContent(file, "Fail");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }

    public void testSearchByContentFail() throws Exception {
        List<String> files = ZipUtility.searchByContent(file, "Stephen King is a bad writer");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }
}