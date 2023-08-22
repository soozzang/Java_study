package OnBoarding;

public class FifthProb {

    public static int[] result(int money, int[] arr_money){
        int tmp = money;
        int[] arr_result = new int[9];
        while(tmp !=0){
            for(int i=0; i<9;i++) {
                if(tmp / arr_money[i] >=1){
                    arr_result[i] = tmp/arr_money[i];
                    tmp -= arr_money[i]*arr_result[i];
                }
            }
        }
        return(arr_result);
    }
    public static void main(String[] args){
        int money = 15000;
        int[] arr_money = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
        int[] result = result(money, arr_money);
        for(int i=0; i<9;i++) {
            System.out.println(result[i]);
        }
    }
}
