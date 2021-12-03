package Lesson3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileIOApp {

    public static void main(String[] args) {
        File file = new File("demo.txt");


        try(FileOutputStream out = new FileOutputStream(file)) {
            long start = System.currentTimeMillis();
            for (int i=0; i < 100_000; i++) {
                out.write(65);
            }
            System.out.println("t1 = " + (System.currentTimeMillis() - start));

            byte[] arr = new byte[100000];
            Arrays.fill(arr, (byte) 65);
            start = System.currentTimeMillis();
            out.write(arr);
            System.out.println("t2 = " + (System.currentTimeMillis() - start));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

       /* try(FileInputStream inputStream = new FileInputStream("demo.txt")){
            while (inputStream.available() > 0) {
                System.out.print((char)inputStream.read());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }
}
