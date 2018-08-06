package ThreadWait;



import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileWait {
    public static void main(String[] args) {
        FileWriteThread t1 = new FileWriteThread(null, "t1");
        FileWriteThread t2 = new FileWriteThread(t1, "t2");
        FileWriteThread t3 = new FileWriteThread(t2, "t3");
        t1.before = t3;

        t1.start();
        t2.start();
        t3.start();
    }
}

class FileLock {
    String lockFlag1 = "t";
    String lockFlag2 = "t";
    String lockFlag3 = "t";
    File file1 = new File("D:/file1");
    File file2 = new File("D:/file2");
    File file3 = new File("D:/file3");

}

class FileWriteThread extends Thread {
    public static final FileLock lock = new FileLock();


    FileWriteThread before;
    String name;

    public FileWriteThread(FileWriteThread before, String name) {
        this.before = before;
        this.name = name;
    }

    @Override
    public void run() {
        switch (name) {
            case "t1":
                System.out.println("file1->" + name);
                write(lock.file1,name);
                synchronized (lock){
                    lock.lockFlag1=name;
                    lock.notifyAll();
                }
                break;
            case "t2":
                System.out.println("file2->" + name);
                write(lock.file2,name);
                synchronized (lock){
                    lock.lockFlag2=name;
                    lock.notifyAll();
                }
                break;
            case "t3":
                System.out.println("file3->" + name);
                write(lock.file3,name);
                synchronized (lock){
                    lock.lockFlag3=name;
                    lock.notifyAll();
                }
                break;
        }
        while (true){
            if(before.name.equals(lock.lockFlag1)){
                System.out.println("file1->" + name);
                write(lock.file1,name);
                synchronized (lock){
                    lock.lockFlag1=name;
                    lock.notifyAll();
                    continue;
                }
            }
            if(before.name.equals(lock.lockFlag2)){
                System.out.println("file2->" + name);
                write(lock.file2,name);
                synchronized (lock){
                    lock.lockFlag2=name;
                    lock.notifyAll();
                    continue;
                }
            }
            if(before.name.equals(lock.lockFlag3)){
                System.out.println("file3->" + name);
                write(lock.file3,name);
                synchronized (lock){
                    lock.lockFlag3=name;
                    lock.notifyAll();
                    continue;
                }
            }
            synchronized (lock){
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void write(File file,String name){
        try {
            FileUtils.write(file, name,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
