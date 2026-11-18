package Logic;
import java.util.*;


public class RNG {
    Random rand = new Random(System.currentTimeMillis());
    /**
     * empty constructor for the class
     * 
     */
    public RNG(){}
    /**
     *  used to generate a choice for the other methods
     * @param value int that will be used as upper bounds 
     * @return int  based on random number generator
     */
    public int roll(int value){
        return (rand.nextInt(value)+rand.nextInt(value)+rand.nextInt(value))%value;
    }
    /**
     * choses a number from a weighted list. begins by rolling a random number
     * checks with target values. returns the value if one matches.
     * increments the rand if no matches found 
     * 
     * @param list ints representing the upper bounds of each potential choice
     * @return int chosen from the list of ints 
     */
    public int rollWeighted(int[] list){
        int out = -1;
        int len = list.length;
        int max = list[len-1];
        int ran = roll(max);
        while(out == -1){
            if (ran >= max){out = max;}
            for (int num : list){
                if ((num > max)){return out;}
                if (ran == num){out = num;}
            }
            ran++;
        }
        return out; 
    }
    /**
     * 
     * @param list
     * @return
     */
    public String pick(String[] list){
        int len = list.length; 
        int ran = roll(len);
        return list[ran];
    }
    /**
     * 
     * @param choices
     * @return
     */
    public String pick(String choices){
        return pick(choices, "/");
    }
    /**
     * 
     * @param choices
     * @param deliminator
     * @return
     */
    public String pick(String choices,String deliminator){
        return pick(choices.split(deliminator));
    }
    /**
     * 
     * @param line
     * @return
     */
    public String pickWeighted(String line){
        String[] list = line.split("/");
        if (list.length < 1){return "ERRORDelimitorNotRecognized";} 
        return pickWeighted(list);
    }
    /**
     * 
     * @param list
     * @return
     */
    public String pickWeighted(String[] list){
        int len = list.length;
        int[] pegs = new int[len];
        String[] choices = new String[len];
        String line = "";
        try{
        for (int i = 0; i < len; i++){
            line = list[i];
            pegs[i] = (int) Integer.parseInt(line.split(":")[0]);
            choices[i] = line.split(":")[1];
        }
        int pick = rollWeighted(pegs);
        for (int i = 0; i < len; i++){
            if(pick == pegs[i]){return choices[i];}
        }
        } catch (Exception e){System.out.println(e);}
        return "ERROR";
        
    }
    /**
     * 
     * @return
     */
    public boolean flip(){
        return roll(100) > 50;  
    }
    /**
     * 
     * @param line
     * @param delim
     * @return
     */
    public String[] listBy(String line, String delim){
        return line.split(delim);
    }

    public boolean isIn(int test, int[] list){
        for (int x : list){
            if (x == test){return true;}
        }
        return false; 
    }
    public int[] draftUnique(int max, int size){
        int[] draft = new int[size]; 
        for (int i = 0; i < size; i++){
            draft[i] = 999;
        }
        int index = 0; 
        while(size > 0){
            int pick = rand.nextInt(max+1)-1;
            if (pick < 0) pick = 0; 
            if (!isIn(pick, draft)){
                draft[index] = pick;
                index++;
                size--;
            }
        }
        return draft;
    }
    public int[] draftAll(int max){
        int[] draft = new int[max]; 
        for (int i = 0; i < max; i++){
            draft[i] = 999;
        }
        int index = 0;
        int tries = max;
        while(tries > 0){
            int pick = rand.nextInt(max+1)-1;
            if (pick < 0) pick = 0; 
            if (!isIn(pick, draft)){
                draft[index] = pick;
                index++;
                tries--;
            }
        }
        return draft;
    }
}
