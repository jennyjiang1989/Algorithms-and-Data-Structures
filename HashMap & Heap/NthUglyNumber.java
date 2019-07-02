//Ugly number is a number that only have factors 2, 3 and 5.
//Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...
import java.util.*;
public class Solution {
    /**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
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
                long newNumber=number*primes[j];
                if(!set.contains(newNumber)){
                    pq.add(newNumber);
                    set.add(newNumber);
                }
            }
        }
        return (int)number;
    }
}
