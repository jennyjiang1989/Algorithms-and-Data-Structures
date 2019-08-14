//You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
//One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
//What is the maximum number of envelopes can you Russian doll? (put one inside other)

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
         if(envelopes==null||envelopes.length==0){
            return 0;
        }
        Arrays.sort(envelopes,(a,b)->(a[0]-b[0]));//按照宽从小到大排
        int max=0;
        int n=envelopes.length;
        int[] f=new int[n];
        for(int i=0;i<n;i++){
            f[i]=1;
            for(int j=0;j<i;j++){
                if(envelopes[j][0]<envelopes[i][0]&&envelopes[j][1]<envelopes[i][1]){
                    f[i]=Math.max(f[i],f[j]+1);
                }
            }
            if(max<f[i]){
                max=f[i];
            }
        }
        return max;
    }
}
