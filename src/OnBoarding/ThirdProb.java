package OnBoarding;

public class ThirdProb {
    public static void main(String[] args){
        int number=33;
        int result = game(number);
        System.out.println(result);
    }

    public static int chk(int i){
        int cnt =0;
        int tmp = i;
        while(tmp!=0){
            int x = tmp%10;
            if(x==3||x==6||x==9){
                cnt+=1;
            }
            tmp /=10;
        }
        return cnt;
    }

    public static int game(int number){
        int real_cnt = 0;
        for(int i=1; i<=number; i++){
            real_cnt += chk(i);
        }
        return  real_cnt;
    }
}
