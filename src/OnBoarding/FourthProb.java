package OnBoarding;

public class FourthProb {

    public static String result(int[] arr, int len){
        char[] new_chars = new char[1005];
        for(int i=0; i<len; i++){
            char new_char;
            if(arr[i]<=90&& arr[i]>32){
                new_char = (char)(65+(90-arr[i]));
            }
            else if(arr[i]>=97){
                new_char = (char)(97+(122-arr[i]));
            }
            else {
                new_char = ' ';
            }
            new_chars[i] = new_char;
            System.out.println(new_chars[i]);
        }
        return new String(new_chars,0,len);
    }
    public static void main(String[] args){
        String word = "I love you";
        int[] arr = new int[1005];
        for(int i=0; i<word.length(); i++){
            char wd = word.charAt(i); //(int)공백 = 32
            arr[i] = wd;
        }
        String result = result(arr,word.length());
        System.out.println((result));
    }
}

