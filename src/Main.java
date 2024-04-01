public class Main {
    public static void main(String[] args) {
        String zipFilePath = "C:\\Games\\savegames\\zip.zip";
        String destDirectory = "C:\\Games\\savegames";
        String filePath = "C:\\Games\\savegames\\data1.dat";


        GameProgress gameProgress = new GameProgress(10, 2, 2, 2.5);
        gameProgress.openZip(zipFilePath, destDirectory);
        System.out.println(gameProgress.openProgress(filePath));
    }
}