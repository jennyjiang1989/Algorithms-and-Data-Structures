/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */    
    public ListNode[] rehashing(ListNode[] hashTable) {
        // write your code here
        if(hashTable==null||hashTable.length==0){
            return hashTable;
        }
        int newCapacity=2*hashTable.length;
        ListNode[] newTable = new ListNode[newCapacity];
        for(int i=0;i<hashTable.length;i++){
            while(hashTable[i]!=null){
                //rehashing
                int newIndex=(hashTable[i].val+newCapacity)%newCapacity;
                if(newTable[newIndex]==null){
                    newTable[newIndex]=new ListNode(hashTable[i].val);
                }else{
                    ListNode dummy=newTable[newIndex];
                    while(dummy.next!=null){
                        dummy=dummy.next;
                    }
                    dummy.next=new ListNode(hashTable[i].val);
                }
                hashTable[i]=hashTable[i].next;
            }
        }
        return newTable;
    }
};
