package Game;


public class Timer implements Runnable{
    int time;

    public Timer(DIFF difficulty){
        try {            
            this.time = difficulty.time;
            System.out.println("You have " + this.time / 60000 + " minutes remaining!");
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