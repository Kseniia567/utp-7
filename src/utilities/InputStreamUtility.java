package utilities;

import java.io.InputStream;
import java.util.Scanner;

public class InputStreamUtility {
    public InputStreamUtility() {}

    public static boolean contains(final InputStream input, final String string, final long size) {
        Scanner scanner = new Scanner(input);
        String result = scanner.findWithinHorizon(string, (int) size); //find pattern in file
        scanner.close();
        return result != null;
    }
}
