package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.13. 21:45
 */
public class Demo09_Daemon {
    public static void main(String[] args) {
        God god = new God();
        Youu you = new Youu();

        Thread thread = new Thread(god);
        //设置上帝为守护线程
        thread.setDaemon(true); //默认是false 表示是用户线程，正常都是用户线程
        thread.start();//上帝守护着你
        new Thread(you).start();
    }
}

//上帝
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you!");
        }
    }
}

//你
class Youu implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("Happy everyday!");
        }
        //当正常线程结束时，程序可以不用等待守护线程执行结束而结束
        System.out.println("Goodby my world==========");
    }
}