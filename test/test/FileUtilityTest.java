package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.FileUtility;

import java.io.File;
import java.util.List;

public class FileUtilityTest {
    File file = new File("c:/UTP7test");

    @Before
    public void before(){
        Assert.assertTrue(file.exists());
    }


    @Test
    public void testSearchByName() throws Exception {
        List<File> files = FileUtility.searchByName(file, "pushkin");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void testSearchByContent() throws Exception {
        List<File> files =FileUtility.searchByContent(file, "angel");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    @Test
    public void testSearchByNameFail() throws Exception {
        List<File> files = FileUtility.searchByName(file, "nekrasov");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }

    @Test
    public void testSearchByContentFail() throws Exception {
        List<File> files = FileUtility.searchByContent(file, "Though it's hard to die, it's good to die");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }
}