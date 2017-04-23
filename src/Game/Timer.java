package Game;

import com.sun.javaws.Main;
import com.sun.javaws.exceptions.ExitException;

public class Timer implements Runnable{
    int time;

    public Timer(String diffuculty){
        try {
            diffuculty = diffuculty.toUpperCase();
            DIFF a = DIFF.valueOf(diffuculty);
            this.time = a.time;
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e){}
        System.out.println("Time's up!");
        System.exit(0);
    }
}

/*
 * in minutes
 */
enum DIFF{
    EASY(60),
    MEDIUM(40),
    HARD(20);

    public int time;

    DIFF(int time){
        this.time = time * 1000 * 60;
    }
}