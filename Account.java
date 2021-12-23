package bankAccount;
import java.util.Scanner;
import java.util.Random;

public class Account extends Thread {
    int balance;

    public Account(int balance) {
        this.balance = balance;
    }
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        balance += random.nextInt(1_000_000);
        System.out.println("Вы выйграли джекпот!!!" + "\nВаш баланс: " + balance + " долларов");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int money;
        byte prov = 0;
        byte prov2 = 0;
        while (true) {
            if(prov == 3){
                System.out.println("\n"+"Вы превысили лимит операций. Попробуйте через 24 часа!");
                break;
            }

            //*****
            else{
                if(prov2 % 2 == 0){ // Без этого условия почему-то строка ниже выводится 2 раза..
                    System.out.println("Что Вы хотите сделать?" + "\nДля пополнения введите +" + "\nДля снятия введите -"); // эта строка
                    prov2++;
                }
                else{
                    prov2++;
                }
            }
            //*********


            String ifElse = in.nextLine();
            if (ifElse.equals("+")) {
                System.out.println("Введите сумму для пополнения");
                money = in.nextInt();
                if (money < 100){
                    System.out.println("Минимальная сумма пополнения 100 долларов" + "\n");
                    prov++;
                    continue;
                }
                upAccount(money);
                prov++;

            }
            else if (ifElse.equals("-")) {
                System.out.println("Введите сумму для снятия");
                money = in.nextInt();
                if(money > balance){
                    System.out.println("У Вас недостаточно средств" + "\n");
                    prov++;
                    continue;
                }
                if (money < 100){
                    System.out.println("Минимальная сумма снятия 100 долларов" + "\n");
                    prov++;
                    continue;
                }
                withdrawMoney(money);
                prov++;
            }
        }
    }
    public void withdrawMoney(int money){
        balance -= money;
        System.out.println("Со счета снято " + money + " долларов" + "\nВаш баланс: " + balance + " долларов");
    }
    public void upAccount(int money){
        balance += money;
        System.out.println("Вам на счет зачислено " + money + " долларов" + "\nВаш баланс: " + balance + " долларов");
    }
}
