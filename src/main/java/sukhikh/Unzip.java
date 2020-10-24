package sukhikh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Kate Sukhikh
 */
public class Unzip {

    /**
     * Процедура деархивации
     *
     * @param zipFileName - имя архива, который необходимо распаковать
     * @param filesPath   - список файлов, которые необходимо вытащить из архива
     */
    public static void unZip(final String zipFileName, List<String> filesPath) throws Exception {

        final ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFileName)); //открываем архив
        ZipEntry ze = zis.getNextEntry();

        while (ze != null) { //забирает сущности из архива, пока они не кончатся

            File nextFile = new File(ze.getName()); //путь до нового файла
            if (filesPath.stream().noneMatch(x ->  x.equals(nextFile.getPath()))) {
                ze = zis.getNextEntry();
                continue;
            }
            File createFile = new File(nextFile.getPath().substring(1));
            if (ze.isDirectory()) { //если сущность оказалась директорий, то создаём директорию
                createFile.mkdir();
            } else { //если сущность оказалась файлом
                createNewFile(createFile, zis);
            }
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    /**
     * Процедура создания файла из архива
     *
     * @param file - файл, который будет создан
     * @param zis  - входной поток для чтения файлов в формате ZIP
     */
    private static void createNewFile(File file, ZipInputStream zis) throws Exception {
        new File(file.getParent()).mkdirs();//создаёт все необходимые директории по пути к файлу
        //запись содержимого в созданный файл
        try (FileOutputStream fos = new FileOutputStream(file)) {

            int length;
            byte[] buffer = new byte[zis.available()];
            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }
    }

}


