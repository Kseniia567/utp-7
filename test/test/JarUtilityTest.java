package test;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import utilities.JarUtility;

import java.io.File;
import java.util.List;

public class JarUtilityTest extends TestCase {

    File file = new File("c:/UTP7test/testJar.jar");



    @Before
    public void before() {
        Assert.assertTrue(file.exists());

    }

    public void testSearchByName() throws Exception {
        List<String> files = JarUtility.searchByName(file, "IT");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    public void testSearchByContent() throws Exception {
        List<String> files = JarUtility.searchByContent(file, "boat bobbed");
        assert files != null;
        Assert.assertEquals(1, files.size());
    }

    public void testSearchByNameFail() throws Exception {
        List<String> files = JarUtility.searchByContent(file, "Stephen King is a bad writer");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }

    public void testSearchByContentFail() throws Exception {
        List<String> files = JarUtility.searchByContent(file, "frufhruhfrhh");
        assert files != null;
        Assert.assertEquals(0, files.size());
    }
}