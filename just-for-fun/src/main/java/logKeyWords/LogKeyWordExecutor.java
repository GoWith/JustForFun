package logKeyWords;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static logKeyWords.LogKeyWordExecutor.key;

/**
 * Created by maoyi on 2018/8/6.
 * don't worry , be happy
 */
public class LogKeyWordExecutor {
    public static final String key = "select";
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        File file = new File("D:/sys_info.log");
        int threadN = 3;
        List<String> list = FileUtils.readLines(file, "UTF-8");

        Executor executor = Executors.newCachedThreadPool();
        CompletionService<Map<String,Integer>> completionService = new ExecutorCompletionService(executor);

        int grep = list.size() / threadN +1;
        int endIndex = -1;
        for (int i = 0; i < threadN; i++) {
            MyCallable myCallable = new MyCallable(endIndex + 1, endIndex + grep , list);
            completionService.submit(myCallable);
        }

        Map<String,Integer> result = new HashMap<>();

        for (int i = 0; i < threadN; i++) {
            Map<String,Integer> map = completionService.take().get();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (result.containsKey(entry.getKey())){
                    result.put(entry.getKey(),result.get(entry.getKey())+entry.getValue());
                }else result.put(entry.getKey(),entry.getValue());
            }
        }
        List<Map.Entry<String,Integer>> list1 = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            list1.add(entry);
        }
        Collections.sort(list1,new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : list1) {
            System.out.println(entry.getValue()+":_:"+entry.getKey());
        }
    }
}
class MyCallable implements Callable<Map<String,Integer>>{

    int minIndex;
    int maxIndex;
    List<String> list;

    public MyCallable(int minIndex, int maxIndex,List<String> list) {
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
        this.list=list;
    }

    @Override
    public Map<String,Integer> call() throws Exception {
        Map<String,Integer> result = new HashMap<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            String s = list.get(i);
            if(s.contains(key)){
                if (result.containsKey(s)){
                    result.put(s,result.get(s)+1);
                }else result.put(s,1);
            }
        }
        return result;
    }
}
