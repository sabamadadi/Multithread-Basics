package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.HashSet;

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    public static class find implements Runnable
    {
        public static int sum;
        public static HashSet<Integer> added;
        public int dev;
        public int n;
        public find(int dev,int n) {
            this.dev = dev;
            this.n=n;
        }
        public static synchronized void check(int i)
        {
            if(!added.contains(i)) {
                sum+=i;
                added.add(i);
            }
        }
        @Override
        public void run() {
            for(int i = 1 ; i <= n ; i++)
            {
                if(i % dev == 0)
                    check(i);
            }
        }
    }
    public int getSum(int n) {
        find.sum=0;
        find.added=new HashSet<>();
        Thread thread1 = new Thread(new find(3,n));
        Thread thread2 = new Thread(new find(7,n));
        Thread thread3 = new Thread(new find(5,n));
        thread3.start();
        thread2.start();
        thread1.start();
        try {
            thread3.join();
            thread2.join();
            thread1.join();
        }catch (InterruptedException e)
        {

        }
        return find.sum;
    }

    public static void main(String[] args) {
    }
}
