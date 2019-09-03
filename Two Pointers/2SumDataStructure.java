//Design and implement a TwoSum class. It should support the following operations: add and find.
//add - Add the number to an internal data structure.
//find - Find if there exists any pair of numbers which sum is equal to the value.
public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
    private HashMap<Integer,Integer> map=new HashMap<>();//key->how many
    public void add(int number) {
        // write your code here
        if(map.containsKey(number)){
            map.put(number,map.get(number)+1);
        }else{
            map.put(number,1);
        }
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        for(int key:map.keySet()){
            if((key*2!=value&&map.containsKey(value-key))||(key*2==value&&map.get(key)>=2)){
                return true;
            }
        }
        return false;
    }
}
