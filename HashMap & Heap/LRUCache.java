//Design a data structure for least recently used cache.
//get(key)-Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//set(key,value)-Set or insert the value if the key is not already present. 
//When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//Doubly linked list + HashMap
import java.util.*;
public class LRUCache {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();//key->Node
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;//开始只有head tail
        head.next = tail;
    }

    public int get(int key) {
	//key找不到
        if( !hs.containsKey(key)) {   
            return -1;
        }

        //remove current node from doubly linked list
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current to tail
	//每次get，使用次数+1，最近使用，放于尾部
        move_to_tail(current);			

        return current.value;
    }
    //数据放入缓存
    public void set(int key, int value) {			
        // get 这个方法会把key挪到最末端，因此，不需要再调用 move_to_tail
        if (get(key) != -1) {
            hs.get(key).value = value;
            return;
        }
        //超出缓存上限
        if (hs.size() == capacity) {
	    //从hashmap中删除第一个node
            hs.remove(head.next.key);	
            //从doubly linked list中删除第一个node
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);	
        //放入hashmap中
        hs.put(key, insert);
	//放入doubly linked list最后
        move_to_tail(insert);					
    }
    //移动数据至尾部
    private void move_to_tail(Node current) { 
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}
