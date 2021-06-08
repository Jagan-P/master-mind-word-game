import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class MasterMind
{
	static ArrayList<String> sowpods=new ArrayList<String>(90000);
        static ArrayList<String> newsowpods=new ArrayList<String>(90000);
	public static void main(String[] args) throws Exception{
		
                Scanner sc=new Scanner(new File("sowpods.txt"));
                Scanner user=new Scanner(System.in);
		
                boolean notFound=true;
                int numberOfTries=0;
                int currentWordIndex=0;
                int gameWordCount=0;
                boolean increaseCurrentWordIndex=true;
                
                System.out.println("What shall we play? A (T)wo letter or (Th)ree letter or (F)our letter or (Fi)ve Letter or (S)ix Letter game?");
                String getUserChoice=user.next();
                
                if(getUserChoice.equals("T"))
                    gameWordCount=2;
                else if(getUserChoice.equals("Th"))
                    gameWordCount=3;
                else if(getUserChoice.equals("F"))
                    gameWordCount=4;
                else if(getUserChoice.equals("Fi"))
                    gameWordCount=5;
                else if(getUserChoice.equals("S"))
                    gameWordCount=6;
                else
                {
                    System.out.println("OOPS try again!\nBye for Now");
                    System.exit(0);
                }
                
                while(sc.hasNext()) {
                    String temp=sc.next();
                    temp.toLowerCase();
                    if(temp.length()==gameWordCount)
                    {
                        if(temp.length()==unique(temp).length())
                                sowpods.add(temp);
                    }
		}
                
                do {
                    System.out.println("Possibilites is "+sowpods.size());
                    System.out.println("How many common alphabets in "+sowpods.get(currentWordIndex)+"?");
                    int count=user.nextInt();
                    numberOfTries++;
                    
                    if(count==gameWordCount) {
                        increaseCurrentWordIndex=false;
                        System.out.println("Is this the word you guessed? (Y) (N)");
                        String decision=user.next();
                        if(decision.equals("Y")) {
                            System.out.println("Computer won the game");
                            System.out.println("Won in "+numberOfTries+" guess(es)");
                            notFound=false;
                        }
                        else {
                            sowpods.remove(sowpods.get(currentWordIndex));
                        }
                        
                    }
                    
                    else {
                        retainWordsWithCount(sowpods.get(currentWordIndex),count);
                        increaseCurrentWordIndex=false;
                    }
                    if(increaseCurrentWordIndex)
                        currentWordIndex++;
                    else {
                        currentWordIndex=0;
                        increaseCurrentWordIndex=true;
                    }
                }while(notFound);
	}
        
        
        static void retainWordsWithCount(String a,int n) {
            char[] temp=a.toCharArray();
            for(int i=0;i<sowpods.size();i++)
            {
                int count=0;
                for(int j=0;j<temp.length;j++)
                {
                    if(sowpods.get(i).contains(temp[j]+""))
                        count++;
                }
                if(count!=n)
                {
                    sowpods.remove(sowpods.get(i));
                    i--;
                }
            }
        }
        
        
        static String unique(String s) 
        { 
            String str = new String(); 
            int len = s.length(); 
            for (int i = 0; i < len; i++)  
            { 
                char c = s.charAt(i); 
                if (str.indexOf(c) < 0) 
                { 
                    str += c; 
                } 
            }
            return str; 
        } 
}
