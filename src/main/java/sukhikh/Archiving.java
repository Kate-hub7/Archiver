package sukhikh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Kate Sukhikh
 *
 */
public class Archiving {

    /**
     * Процедура архивации
     * @param files - спискок файлов и директорий для архивации
     */
    public static void archiving(List<File> files) throws Exception {

        FileOutputStream fout = new FileOutputStream("newZip.zip"); //какой архив создать
        System.out.println("newZip.zip");
        ZipOutputStream zout = new ZipOutputStream(fout);

        for(int i=0;i<files.size();i++){
            if(files.get(i).isFile()){
                addFile(zout, files.get(i));
                continue;
            }
            if(files.get(i).isDirectory()){
                addDirectory(zout, files.get(i));
            }

        }
        zout.close();
        fout.close();
    }
    /**
     * Процедура архивации
     * @param zout - выходной поток для записи файлов в формате ZIP
     * @param fileSource - директория для добавления в архив
     */
    private static void addDirectory(ZipOutputStream zout, File fileSource) throws Exception {

        File[] files = fileSource.listFiles();
        if(files==null)
            return;

        System.out.println(fileSource.getPath());

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addDirectory(zout, files[i]);
                continue;
            }
            if(files[i].isFile()){
                addFile(zout, files[i]);
            }

        }
    }
    /**
     * Процедура архивации
     * @param zout - выходной поток для записи файлов в формате ZIP
     * @param file - файл для добавления в архив
     */
    private static void addFile(ZipOutputStream zout, File file) throws Exception{

        System.out.println(file.getPath());
        FileInputStream fis = new FileInputStream(file);

        zout.putNextEntry(new ZipEntry(file.getPath()));

        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        zout.write(buffer);

        zout.closeEntry();
        fis.close();
    }
}
