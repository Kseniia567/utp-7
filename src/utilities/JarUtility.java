package utilities;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JarUtility {
    private JarUtility() {}

    public static List<String> searchByName(final File file, final String name) throws Exception {
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try {
            JarFile jar = new JarFile(file);
            Predicate<JarEntry> matchName = jarEntry -> jarEntry.getName().contains(name);

            List<String> names = jar
                    .stream()
                    .parallel()
                    .filter(matchName)
                    .map(JarEntry::getName)
                    .collect(Collectors.toList());
            jar.close();
            return names;

        }catch(Exception e) {
            throw new Exception("Error in method search by name (Jar). Please, try again");
        }
    }


    public static List<String> searchByContent(final File file, final String content) throws Exception {
        if (!file.exists() || !file.isFile()) {
            return null;
        }

        try {
            JarFile jar = new JarFile(file);
            Predicate<JarEntry> matchContent = jarEntry -> {
                try {
                    return contentSearch(jarEntry, jar, content);
                } catch (Exception e) {
                    return false;
                }
            };



            List<String> names = jar
                    .stream()
                    .parallel()
                    .filter(matchContent)
                    .map(JarEntry::getName)
                    .collect(Collectors.toList());
            jar.close();

            return names;


        }catch(Exception e) {
            throw new Exception("Error in method search by content (Jar). Please, try again");
        }

    }


    private static boolean contentSearch(final JarEntry entry, final JarFile jar, final String content) throws Exception {
        try {
            InputStream inputStream = jar.getInputStream(entry); //returns the input stream
            return InputStreamUtility.contains(inputStream, content, entry.getSize());
        } catch (Exception e) {
            throw new Exception("Error in utility method(Jar)");
        }
    }



}
