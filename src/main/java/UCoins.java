import java.util.HashMap;
import java.util.Scanner;

public class UCoins {
    public static void main(String[] args) {
        int[] utopiaCoins = {1,7,10,22};
        while(true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the sum of coins: ");
            int coinSum = input.nextInt();
            HashMap<Integer,Integer> completedSolutions = new HashMap<>();
            UCoins uCoins = new UCoins();
            /*
            preloading if the coin number is above 5000, to avoid stackoverflowerror exception
             */
            if (coinSum > 5000) {
                int number = 5000;
                do {
                    uCoins.minimumCoinAmount(utopiaCoins, number, completedSolutions);
                    number += 1000;
                } while (number < coinSum && number!=1000000);
                do {
                    uCoins.minimumCoinAmount(utopiaCoins, number, completedSolutions);
                    number += 10000;
                } while (number < coinSum);
            }
            System.out.println("Amount of coins: " + uCoins.minimumCoinAmount(utopiaCoins, coinSum, completedSolutions) +
                    " to get sum of: " + coinSum);

        }
    }
    int minimumCoinAmount(int[] coins, int coinSum, HashMap<Integer,Integer> completedSolutions){
        int change = Integer.MAX_VALUE;
        int newChange = Integer.MIN_VALUE;
        if(coinSum<=0){
            return 0;
        }
        for(int i=0;i<coins.length;i++){
            if(coins[i]==coinSum){
                return 1;
            }
        }
        if(completedSolutions.containsKey(coinSum)){
            return completedSolutions.get(coinSum);
        }
        for(int i=0;i<coins.length;i++){
            if(coinSum-coins[i]>=0){
                newChange = minimumCoinAmount(coins,coinSum-coins[i],completedSolutions)+1;
                completedSolutions.put(coinSum,newChange);
            }
        }

        return Math.min(change,newChange);
    }
}
