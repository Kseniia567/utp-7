package utilities;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtility {

    private ZipUtility(){}




    public static List<String> searchByName(final File file, final String name) throws Exception {
        if (!file.isFile() || !file.exists()) {
            return null;
        }

        try {
            ZipFile zip = new ZipFile(file);
            Predicate<ZipEntry> matchName = zipEntry -> zipEntry.getName().contains(name);
            List<String> names = zip
                    .stream()
                    .parallel()
                    .filter(matchName)
                    .map(ZipEntry::getName)
                    .collect(Collectors.toList());
            zip.close();
            return names;

        }catch(Exception e) {
            throw new Exception("Error in method search by name (Zip). Please, try again");
        }
    }



    public static List<String> searchByContent(final File file, final String content) throws Exception {
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try {
            ZipFile zip = new ZipFile(file);
            Predicate<ZipEntry> matchContent = zipEntry -> {
                try {
                    return contentSearch(zipEntry, zip, content);
                } catch (Exception e) {
                    return false;
                }
            };



            List<String> names = zip
                    .stream()
                    .parallel()
                    .filter(matchContent)
                    .map(ZipEntry::getName)
                    .collect(Collectors.toList());
            zip.close();

            return names;

        } catch(Exception e) {
           throw new Exception("Error in method search by content (Zip). Please, try again");
        }
    }



    private static boolean contentSearch(final ZipEntry entry, final ZipFile zip, final String content) throws Exception {
        try {

            InputStream input = zip.getInputStream(entry);
            return InputStreamUtility.contains(input, content, entry.getSize());

        } catch (Exception e) {
            throw new Exception("Error in utility method(Zip)");
        }
    }
}
