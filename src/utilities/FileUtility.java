package utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtility {
    private FileUtility(){}





   public static List<File> searchByName(final File directory, final String name) throws Exception {
       if(!directory.exists() || !directory.isDirectory() ||!directory.canRead()) {
           return null;
       }

       try {
           Predicate<Path> matchNames = path -> path.toFile()
                                                .getName()
                                                .contains(name);

           Predicate<Path> isFile = Files::isRegularFile;
           Path path = directory.toPath();
           return Files
                   .walk(path)
                   .parallel()
                   .filter(isFile.and(matchNames))
                   .map(Path::toFile)
                   .collect(Collectors.toList());
       } catch (Exception e) {
           throw new Exception("Error in method search by name (Files). Please, try again");
       }
   }

   public static List<File> searchByContent (final File directory, final String content) throws Exception {
        if (!directory.exists() || !directory.isDirectory() || !directory.canRead()) {
            return null;
        }

        try{
            Predicate<Path> isFile = Files::isRegularFile;

            Predicate<Path> matchContent = path -> {
                try {
                    return contentSearch(path.toFile(), content);
                } catch (Exception e) {
                    return false;
                }
            };


            Path path = directory.toPath();
            return Files
                    .walk(path)
                    .parallel()
                    .filter(isFile.and(matchContent))
                    .map(Path::toFile)
                    .collect(Collectors.toList());


        }catch(Exception e){
            throw new Exception("Error in method search by content (Files). Please, try again");
        }
   }


   private static boolean contentSearch(final File file, final String content) throws Exception {
        if (file.isDirectory()) {
            return false;
        }

       try {
           InputStream inputStream = new FileInputStream(file);
           return InputStreamUtility.contains(inputStream, content, file.length());
       } catch (Exception e) {
           throw new Exception("Error in utility method(Files)");
       }
   }


}
