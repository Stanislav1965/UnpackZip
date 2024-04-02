import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        String zipFilePath = "C:\\Games\\savegames\\zip.zip";
        String destDirectory = "C:\\Games\\savegames";
        String filePath = "C:\\Games\\savegames\\data1.dat";

        openZip(zipFilePath, destDirectory);
        System.out.println(openProgress(filePath));
    }

    public static void openZip(String zipFilePath, String destDirectory) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream(zipFilePath))) {

            File destDir = new File(destDirectory);
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                String fileName = null;
                String filePath = entry.getName();
                int lastPath = filePath.lastIndexOf(File.separator);
                if (lastPath != -1) {
                    fileName = filePath.substring(lastPath + 1);
                }
                String filePathDest = destDir + File.separator + fileName;
                if (!entry.isDirectory()) {
                    FileOutputStream fout = new FileOutputStream(filePathDest);
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }
                    fout.flush();
                    fout.close();
                }
                zin.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static GameProgress openProgress(String filePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }
}