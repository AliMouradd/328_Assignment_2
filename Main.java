import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(new File("input.txt"));
        String stringN = reader.next();
        int n = Integer.parseInt(stringN);
        ArrayList<Integer> steinNumbers = new ArrayList<>();
        ArrayList<BigInteger> divisorNums = new ArrayList<>();
        ArrayList<Integer> score = new ArrayList<>();
        for(int i = 0; i < n; i++){
            steinNumbers.add(Integer.parseInt(reader.next()));
        }
        while(reader.hasNext()){
            divisorNums.add(new BigInteger(reader.next()));
        }
        reader.close();
        int tempScore = 0;
        BigInteger a;
        BigInteger b;
        for(int i = 0; i < divisorNums.size(); i+=2){
            a = divisorNums.get(i);
            b = divisorNums.get(i+1);
            int count = 0;
            while (count < steinNumbers.size()) {
                if (a.remainder(BigInteger.valueOf(steinNumbers.get(count))).equals(BigInteger.ZERO) & b.remainder(BigInteger.valueOf(steinNumbers.get(count))).equals(BigInteger.ZERO)) {
                    a = a.divide(BigInteger.valueOf(steinNumbers.get(count)));
                    b = b.divide(BigInteger.valueOf(steinNumbers.get(count)));
                    tempScore += 2;
                } else if (a.remainder(BigInteger.valueOf(steinNumbers.get(count))).equals(BigInteger.ZERO)) {
                    a = a.divide(BigInteger.valueOf(steinNumbers.get(count)));
                    tempScore = tempScore+1;
                } else if (b.remainder(BigInteger.valueOf(steinNumbers.get(count))).equals(BigInteger.ZERO)) {
                    b = b.divide(BigInteger.valueOf(steinNumbers.get(count)));
                    tempScore = tempScore+1;
                } else {
                    a = divisorNums.get(i);
                    b = divisorNums.get(i +1);
                    if(count >= score.size()){
                        score.add(tempScore);
                    }else{
                        int temp = score.get(count)+ tempScore;
                        score.add(count, temp);
                        score.remove((count+1));
                    }
                    count++;
                    tempScore = 0;
                }
            }
        }
        FileWriter myWriter = new FileWriter("output.txt");
        for(int i = 0; i < steinNumbers.size(); i++){
            myWriter.write(steinNumbers.get(i) + " " + score.get(i) + "\n");
        }
        myWriter.close();
    }
}


