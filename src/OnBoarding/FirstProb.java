package OnBoarding;
public class FirstProb {
    public static int input_test(int[] arr){
        for(int i=0; i<=1; i++) {
            if(arr[i]<1 || arr[i]>400) {
                System.out.println("1부터 400 사이의 값을 넣으세요.");
                return -1;
            }
            if (i==0){
                if(arr[i]%2==0) {
                    System.out.println("첫 페이지는 홀수입니다.");
                    return -1;
                }
            }
            if (i==1){
                if(arr[i]!=arr[i-1]+1) {
                    System.out.println("연속된 두 수를 넣으세요.");
                    return -1;
                }
            }
        }
        return 0;
    }

    public static int sm(int a){
        int sm = 0 ;
        int tmp = a;
        while(tmp!=0){
            sm += tmp%10;
            tmp /= 10;
        }
        return sm;
    }

    public static int mul(int a){
        int mul = 1;
        int tmp = a;
        while(tmp!=0){
            mul *= tmp%10;
            tmp /= 10;
        }
        return mul;
    }

    public static int solution(int[] pobi,int[] crong){
        int pobi_flag = input_test(pobi);
        int crong_flag = input_test(crong);
        int result;

        if(pobi_flag ==-1 || crong_flag == -1){
            return -1;
        }

        int pobi_sm = Math.max(sm(pobi[0]), mul(pobi[0]));
        int pobi_mul = Math.max(sm(pobi[1]), mul(pobi[1]));
        int pobi_score = Math.max(pobi_sm,pobi_mul);

        int crong_sm = Math.max(sm(crong[0]), mul((crong[0])));
        int crong_mul = Math.max(mul(crong[1]), mul((crong[1])));
        int crong_score = Math.max(crong_sm,crong_mul);

        if(pobi_score == crong_score){
            result = 0;
            return result;
        } else if (pobi_score > crong_score) {
            result = 1;
            return result;
        }
        else{
            result = 2;
            return result;
        }
    }

    public static void main(String[] args) {
        int[] pobi = {99, 102};
        int[] crong = {211, 212};

        int result = solution(pobi,crong);
        System.out.println(result);
    }
}