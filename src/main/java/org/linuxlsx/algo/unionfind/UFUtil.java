package org.linuxlsx.algo.unionfind;

import org.linuxlsx.algo.Stopwatch;
import org.linuxlsx.algo.utils.FileUtil;

import java.io.BufferedReader;

/**
 * User: niba
 * Date: 13-8-4
 * Time: 下午5:23
 */
public class UFUtil {


    public static void run(AbstractUF uf, String path){
        BufferedReader reader = FileUtil.reader(path);
        try {

            Stopwatch stopwatch = new Stopwatch();

            String line = reader.readLine();
            int totalCount = Integer.parseInt(line);
            uf.init(totalCount);

            line = reader.readLine();
            int lineNum = 1;
            while (line != null && !"".equals(line.trim())) {
                String[] pairs = line.split(" ");
                if (pairs.length != 2) {
                    continue;
                }

                int p = Integer.parseInt(pairs[0]);
                int q = Integer.parseInt(pairs[1]);

                if (uf.connected(p, q)) {
                    line = reader.readLine();
                    continue;
                }

                uf.union(p, q);
                line = reader.readLine();

                lineNum++;
                System.out.println("read line " + lineNum);
            }

            /*for (int i = 0; i < uf.id.length; i++) {
                System.out.print(uf.id[i] + " ");
            }*/

            System.out.println(uf.count);
            System.out.println(stopwatch.elapsedMillis());

        } catch (Exception e) {
            //Ignore
            e.printStackTrace();
        }
    }
}
