//Ugly number is a number that only have factors 2, 3 and 5.
//Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
import java.util.*;
public class Solution {
    /**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    //O(nlogn)
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq=new PriorityQueue<>();
        HashSet<Long> set=new HashSet<>();
        long[] primes=new long[3];
        primes[0]=(long)2;
        primes[1]=(long)3;
        primes[2]=(long)5;
        
        for(int i=0;i<3;i++){
            pq.add(primes[i]);
            set.add(primes[i]);
        }
    
        long number=(long)1;
        for(int i=1;i<n;i++){
            number=pq.poll();
            for(int j=0;j<3;j++){
                long newNumber=primes[j]*number;
                if(!set.contains(newNumber)){
                    pq.add(newNumber);
                    set.add(newNumber);
                }
            }
        }
        return (int)number;
    }
}


//Write a Java program to check whether a given number is an ugly number.
//In number system, ugly numbers are positive numbers whose only prime factors are 2, 3 or 5. First 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12. By convention, 1 is included.

//SLOW
public class Solution {
    /**
     * @param num: An integer
     * @return: true if num is an ugly number or false
     */
    public boolean isUgly(int num) {
        // write your code here
        while(num!=1){
            if(num%2==0){
                num/=2;
            }else if(num%3==0){
                num/=3;
            }else if(num%5==0){
                num/=5;
            }else{
                return false;
            }
        }
        return true;
    }
}

//QUICKER
public class Solution {
    /**
     * @param num: An integer
     * @return: true if num is an ugly number or false
     */
    public boolean isUgly(int num) {
        // write your code here
        if(num==0){
            return false;
        }
        if(num==1){
            return true;
        }
        if(num%2==0){
            num=num/2;
            return isUgly(num);
        }
        if(num%3==0){
            num=num/3;
            return isUgly(num);
        }
        if(num%5==0){
            num=num/5;
            return isUgly(num);
        }
        return false;
    }
}
