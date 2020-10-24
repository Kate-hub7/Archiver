package sukhikh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kate Sukhikh
 */
public class Main {

    public static void main(String[] args) {

        try {
            if(args.length != 0){

                List<File> fileSource = new ArrayList<>();
                for(int i=0;i< args.length;i++)
                    fileSource.add(new File(args[i]));

                Archiving.archiving(fileSource);//архивирование директории
                }
            else {

                Scanner in = new Scanner(System.in);
                String nameZip = null;
                List<String> files = new ArrayList<>();

                while (in.hasNextLine()){

                    if(nameZip==null){
                        nameZip = in.nextLine();
                        continue;
                    }
                    files.add(in.nextLine());
                }
                Unzip.unZip(nameZip, files);//деархивирование
            }


       //
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
