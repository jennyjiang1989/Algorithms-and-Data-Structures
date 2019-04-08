//The array is so big that you can't get the length of the array
//You can only access the kth number by ArrayReader.get(k)
//Find the first index of a target number in O(logk)

/**
 * Definition of ArrayReader:
 * class ArrayReader {
 *      // get the number at index, return -1 if index is less than zero.
 *      public int get(int index);
 * }
 */
public class SearchBigSortedArray {
	public int searchBigSortedArray(ArrayReader reader, int target){
		//1.get the index that ArrayReader.get(index)>=target in o(logk)		
		int index=0;
		while(reader.get(index)<target){
			index=index*2+1;
		}
		//2.Binary search the target between 0 and index
		int start=0, end=index;
		while(start+1<end){
			int mid=start+(end-start)/2;
			if(reader.get(mid)<target){
				start=mid;//有重复元素情况
			}else{
				end=mid;
			}
		}
		if(reader.get(start)==target){
			return start;
		}
		if(reader.get(end)==target){
			return end;
		}
		return -1;
	}
}
