package homework5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void SaveBackup() throws IOException {
        Files.createDirectory(Path.of("./backup"));

        DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of("."));
        for (Path file : dir) {
            if (!Files.isDirectory(file)) {
                Files.copy(file, Path.of("./backup/" + file.toString()));
            }
        }
    }

    public static void SaveStage(int [] arr) throws IOException {
        FileOutputStream fos = new FileOutputStream("save.out");
        for (int b = 0; b < 3; b++) {
            byte wr = 0;
            for (int v = 0; v < 3; v++) {
                wr += (byte) (arr[3 * b + v] << (v * 2));
            }
            fos.write(wr);
        }
        fos.flush();
        fos.close();
    }


    public static void main(String[] args) {
        try {
            SaveBackup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int[] arr = {0, 1, 2, 3, 0, 1, 2, 3, 0};
        try {
            SaveStage(arr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
