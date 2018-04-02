package wordladder;


import java.io.*;
import java.util.*;

public class Wordladder {
    /*several global variable*/
    //the queue containing stacks of words
    public static Queue<Stack<String>>ladder = new LinkedList<Stack<String>>();
    //all words in the dictionary
    public static Set<String>wordSet = new HashSet<String>();
    //the already-used neighbor
    public static Set<String>used_wordSet = new HashSet<String>();
    public static String alp = "abcdefghijklmnopqrstuvwxyz";
    private static boolean ladder_exist = false;
    public static String word1="";
    public static String word2="";
    public static void neighbor(String w,String w2,Stack<String>curs) {
        // add the neighbor words to the stack
        int len = w.length();
        for (int i = 0; i<len; ++i) {
            StringBuffer neigh = new StringBuffer(w);
            for (int j = 0; j < alp.length();j++) {
                neigh.setCharAt(i,alp.charAt(j));
                String neighStr = new String(neigh);
                if (wordSet.contains(neighStr) &&
                        !used_wordSet.contains(neighStr)) {
                    Stack<String>newStack = (Stack<String>)curs.clone();
                    newStack.add(neighStr);
                    used_wordSet.add(neighStr);
                    ladder.offer(newStack);
                }
            }
        }
    }

    private static Ladder ladder_to_word(String word2, Stack<String>cur_stack) {
        //find the ladder ending with word2
        while (ladder.size() != 0) {
            cur_stack = ladder.peek();
            String cur_word = cur_stack.peek();
            if (cur_word.equals(word2)) {
                ladder_exist = true;
                return printLadder(word1,word2,cur_stack);
            }
            else
                neighbor(cur_word,word2,cur_stack);
            ladder.poll();
        }
        return printLadder(word1,word2,cur_stack);
    }

    private static Ladder printLadder(String word1, String word2, Stack<String>cur_stack) {
        if (ladder_exist) {
            int size = cur_stack.size();
            String[] result = new String[size];
            for (int i = 0; i < size; ++i) {
                String w = cur_stack.peek();
                result[i] = cur_stack.peek();
                //System.out.print(cur_stack.peek()+" ");
                cur_stack.pop();
            }
            return new Ladder(0,"",result);
        }
        else
            return new Ladder(1,"No word ladder found from "+word1+" back to "
                    +word2+".",new String[]{});
    }

    public static Ladder searchLadder(String w1,String w2){
        //complete process of finding ladder
        Stack<String>cur_stack = new Stack<String>();
        Stack<String>wStack = new Stack<String>();
        used_wordSet.add(w1);
        wStack.push(w1);
        ladder.offer(wStack);
        return ladder_to_word(w2,cur_stack);
    }

    public static void clear(){
        //clear the collections to get ready for the new loop
        ladder.clear();
        used_wordSet.clear();
        ladder_exist = false;
    }


    public  Ladder GetLadder(String fileName, String word1, String word2) throws IOException
    {

            Ladder result;
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
            File in = new File(fileName);
            if (!in.exists()) {
                return new Ladder(1,"Unable to open that file.",new String[]{});
            }

            FileReader file = new FileReader(fileName);
            BufferedReader read_file = new BufferedReader(file);
            boolean eof = false;
            String line;
            while(true) {
                line = read_file.readLine();
                if (line == null)
                    break;
                for (String word : line.split(" ")) {
                    wordSet.add(word);
                }
            }
            while (true) {

                if(word1.equals("")){
                    return new Ladder(1,"word1 is empty",new String[]{});
                }

                if(word2.equals("")){
                    return new Ladder(1,"word2 is empty",new String[]{});
                }
                word1 = word1.toLowerCase();
                word2 = word2.toLowerCase();
                //several valid tests
                if(word1.length() != word2.length())
                    return new Ladder(1,"The two words must be the same length.",new String[]{});
                else if (word1.equals(word2))
                    return new Ladder(1,"The two words must be different.",new String[]{});
                else {

                    if (!(wordSet.contains(word1) && wordSet.contains(word2)))
                        return new Ladder(1,"The two words must be found in the dictionary.",new String[]{});
                    else
                        result = searchLadder(word1, word2);
                }
                clear();
                return result;
            }
    }

}
