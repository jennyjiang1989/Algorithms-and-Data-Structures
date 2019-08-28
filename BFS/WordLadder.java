//Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.
//Transformation rule such that:
//Only one letter can be changed at a time
//Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )

public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict==null){
            return 0;
        }
        
        if(start.equals(end)){
            return 1;
        }
        
        dict.add(start);
        dict.add(end);
        
        Queue<String> queue=new LinkedList<>();
        HashSet<String> set=new HashSet<>();
        queue.offer(start);
        set.add(start);
        
        int steps=1;
        while(!queue.isEmpty()){
            steps++;
            int size=queue.size();
            for(int i=0;i<size;i++){
                String word=queue.poll();
                for(String nextWord:getNextWords(word,dict)){
                    if(set.contains(nextWord)){
                        continue;
                    }
                    if(nextWord.equals(end)){
                        return steps;
                    }
                    
                    queue.offer(nextWord);
                    set.add(nextWord);
                }
            }
        }
        return 0;
    }
    
    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s,int index,char c){
        char[] chars=s.toCharArray();
        chars[index]='c';
        return new String(chars);
    }
    
    // get connections with given word.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getNextWords(String word,Set<String> dict){
        ArrayList<String> nextWords=new ArrayList<>();
        for(char c='a';c<='z';c++){
            for(int i=0;i<word.length();i++){
                if(c==word.charAt(i)){
                    continue;
                }
                String nextWord=replace(word,i,c);
                if(dict.contains(nextWord)){
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }
}
