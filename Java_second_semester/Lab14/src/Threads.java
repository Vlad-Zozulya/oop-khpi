package ua.khpi;

public class Threads {
	public static void task_1() throws InterruptedException {
		int count = 0;
        for (Account account : MenuHelper.accounts) {
            if (!Thread.currentThread().isInterrupted()) {
                if(account.getName().indexOf("Jack") >= 0) { 
                	count++;
                }
				Thread.sleep(100);
            } else {
                throw new InterruptedException();
            }
        }
        System.out.println("First Task finished. People with name Jack : " + count);
	}
	public static void task_2() throws InterruptedException {
		int count = 0;
        for (Account account : MenuHelper.accounts) {
            if (!Thread.currentThread().isInterrupted()) {
                if(AccountHelper.check_vodafone(account.getMobileNumbers())) { 
                	count++;
                }
				Thread.sleep(100);
            } else {
                throw new InterruptedException();
            }
        }
        System.out.println("Second Task finished. People with vodafone number : " + count);
	}
	public static void task_3() throws InterruptedException {
		int count = 0;
        for (Account account : MenuHelper.accounts) {
            if (!Thread.currentThread().isInterrupted()) {
                if(account.getMobileNumbers().size() == 2) { 
                	count++;
                }
				Thread.sleep(100);
            } else {
                throw new InterruptedException();
            }
        }
        System.out.println("Third Task finished. People with 2 mobile numbers : " + count);
	}
}
class FirstThread implements Runnable {
	public void run() {
        System.out.println("First Thread started");
        try {
        	Threads.task_1();
            System.out.println("First Thread finished");
        } catch (InterruptedException e) {
            System.out.println("First Thread is interrupted");
        }
    }
} 
class SecondThread implements Runnable {
	public void run() {
        System.out.println("Second Thread started");
        try {
        	Threads.task_2();
            System.out.println("Second Thread finished");
        } catch (InterruptedException e) {
            System.out.println("Second Thread is interrupted");
        }
    }
} 
class ThirdThread implements Runnable {
	public void run() {
        System.out.println("Third Thread started");
        try {
        	Threads.task_3();
            System.out.println("Third Thread finished");
        } catch (InterruptedException e) {
            System.out.println("Third Thread is interrupted");
        }
    }
} 
