package logKeyWords;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by maoyi on 2018/8/6.
 * don't worry , be happy
 */
public class LogKeyWord {
    public static void main(String[] args) throws Exception{
        File file = new File("D:/sys_info.log");
        String key = "Login";
        int threadN = 3;
        List<String> list = FileUtils.readLines(file, "UTF-8");

        int size = list.size();
        int grep = size / threadN +1;
        int remain = size % threadN;
        int endTemp = -1;
        for (int i = 0; i < threadN; i++) {
            if(i<remain) {
                new CountThread(grep, endTemp + 1, endTemp + grep);
                endTemp += grep;
            } else {
                new CountThread(grep - 1, endTemp + 1, endTemp + grep - 1);
                endTemp = endTemp + grep -1;
            }
        }

    }
}
class CountThread extends Thread{
    int grep = 0;
    int maxIndex = 0;
    int minIndex = 0;

    public CountThread(int grep, int maxIndex, int minIndex) {
        this.grep = grep;
        this.maxIndex = maxIndex;
        this.minIndex = minIndex;
    }

    @Override
    public void run() {

    }
}
