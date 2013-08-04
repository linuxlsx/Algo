package org.linuxlsx.algo.utils;

import java.io.*;

/**
 * User: niba
 * Date: 13-7-23
 * Time: 上午12:43
 */
public class FileUtil {

    public static BufferedReader reader(String filePath){
        if (filePath == null) {
            return null;
        }

        File file = new File(filePath);
        if (file.exists() && file.isFile()){
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                //Ignore
            }
        }


        return null;
    }
}
