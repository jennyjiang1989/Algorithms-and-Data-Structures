//对于任意的key,得到一个固定且无规律的介于0~capacity-1的整数
//33 数太大-影响计算速度 数太小-冲突太多
//饱和度=实际存储元素个数/总共开辟的空间大小 size/capacity>10%,进行rehash
public class Solution {
    /**
     * @param key: A string you should hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(char[] key, int HASH_SIZE) {
        // write your code here
        long hashcode=0;
        for(int i=0;i<key.length;i++){
            hashcode=hashcode*33+(int)key[i];
            hashcode=hashcode%HASH_SIZE;
        }
        return (int)hashcode;
    }
}
