package kais.demo_thread;

/**
 * @author kais
 * @date 2022.02.13. 22:03
 */
//线程同步--同步块
//不安全取钱 （出现问题：账户余额为负） //用synchronized(Obj){}
//两个人去银行取钱，账户
public class Demo11_UnSafeBank {
    public static void main(String[] args) {
        Account account = new Account(100, "结婚基金");
        //你需要从结婚基金中取出50元
        Drawing you = new Drawing(account, 50, "你");
        //你的girlfriend需要从结婚基金中取出100元
        Drawing girlFriend = new Drawing(account, 100, "girlFriend");
        you.start();
        girlFriend.start();
    }
}

//账户
class Account {
    int money; //余额
    String name; //卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread {
    Account account;//账户
    //取了多少钱
    int drawingMoney;
    //现在手里有多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //synchronized 默认锁的是this //同步块
    @Override
    public void run() {
        //锁的对象是变化的量，增删改
        synchronized (account) {   //锁定账户
            //判断是否还有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够了");
                return;
            }
            //sleep可以放大事情的发生性
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //卡内余额
            account.money = account.money - drawingMoney;
            //手里的钱
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);
            //Thread.currentThread().getName() =  this.getName()
            System.out.println(this.getName() + "手里的钱" + nowMoney);
        }
    }
}
