package OnBoarding;

public class SecondProb {
    public static void main(String[] args){
        String cryptogram = "zyelleyz"	;
        String result=solution(cryptogram);
        System.out.println(result);
    }
    public static String solution(String cryptogram) {
        String result = chk(cryptogram);
        return result;
    }

    public static String chk(String cryptogram){
        while(true){
            int len = cryptogram.length();
            int flag=0;
            for(int i=1; i<len; i++){
                if(cryptogram.charAt(i)==cryptogram.charAt(i-1)) {
                    String new_str = cryptogram.substring(0, i-1) + cryptogram.substring(i + 1);
                    cryptogram = new_str;
                    flag=1;
                    break;
                }
            }
            if(flag==0) {
                return cryptogram;
            }
        }
    }
}
