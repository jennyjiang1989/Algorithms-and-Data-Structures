//Write a Java program to check whether a given number is an ugly number.
//In number system, ugly numbers are positive numbers whose only prime factors are 2, 3 or 5. First 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12. By convention, 1 is included.

SLOW: 
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

QUICKER:
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
