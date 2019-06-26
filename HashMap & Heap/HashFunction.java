//对于任意的key,得到一个固定且无规律的介于0~capacity-1的整数
//31 数太大-影响计算速度 数太小-冲突太多
//饱和度=实际存储元素个数/总共开辟的空间大小 size/capacity>10%,进行rehash
public class HashFunction {
	private int HASH_TABLE_SIZE;
	public int hashFunc(String key){
		int sum=0;
		for(int i=0;i<key.length();i++){
			sum=sum*31+(int)(key.charAt(i));
			sum=sum%HASH_TABLE_SIZE;
		}
		return sum;
	}
}
