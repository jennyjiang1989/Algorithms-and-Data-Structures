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
		public Node(int key, int value){
			this.key=key;
			this.value=value;
			this.prev=null;
			this.next=null;
		}
	}
	
	private int capacity;
	private HashMap<Integer,Node> map=new HashMap<>();
	private Node head=new Node(-1,-1);
	private Node tail=new Node(-1,-1);
	
	public LRUCache(int capacity){
		this.capacity=capacity;
		tail.prev=head;
		head.next=tail;
	}
	public int get(int key){
		if(!map.containsKey(key)){
			return -1;
		}
		//remove current
		Node current=map.get(key);
		current.prev.next=current.next;
		current.next.prev=current.prev;
		//move current to tail
		move_to_tail(current);
		return map.get(key).value;//最后return
	}
	private void move_to_tail(Node current){
		current.prev=tail.prev;
		tail.prev=current;
		current.prev.next=current;
		current.next=tail;
	}
	public void set(int key, int value){
		if(get(key)!=-1){
			map.get(key).value=value;
			return;
		}
		
		if(map.size()==capacity){
			map.remove(head.next.key);
			head.next=head.next.next;
			head.next.prev=head;
		}
		
		Node insert=new Node(key,value);
		map.put(key, insert);
		move_to_tail(insert);
	}
}
