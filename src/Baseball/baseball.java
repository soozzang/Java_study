package Baseball;
import java.util.*;
public class baseball {
    public static ArrayList<Integer> make_computer_num(){
        ArrayList<Integer> tmp_num = new ArrayList<>();
        Random random = new Random();
        while(tmp_num.size()<3){
            int rdm = random.nextInt(9);
            if(!tmp_num.contains(rdm)){
                tmp_num.add(rdm);
            }
        }
        return tmp_num;
    }

    public static int[] judge(ArrayList<Integer> user_num,ArrayList<Integer> computer_num){
        int[] try_result = new int[3]; //[볼,스트라이크,낫싱] , 다 맞추면 모든 요소가 0.
        if(user_num.equals(computer_num)){
            return try_result;
        }
        int cnt = 0;
        for(int i=0;i<3;i++){
            int tmp_user_num = user_num.get(i); int tmp_computer_num = computer_num.get(i);
            if(computer_num.contains(tmp_user_num)&&tmp_user_num==tmp_computer_num){
                try_result[1]+=1; //스트라이크
            }
            else if(computer_num.contains(tmp_user_num)&&tmp_user_num!=tmp_computer_num){
                try_result[0]+=1; //볼
            }
            else{
                cnt+=1;
            }
        }
        if(cnt==3){
            try_result[2]=1; // 세번 돌았는데 모두 안겹치면 nothing
        }
        return try_result;
    }

    public static ArrayList<Integer> make_user_num(int num){
        ArrayList<Integer> user_num = new ArrayList<>();
        while(num >0){
            int tmp_num = num%10;
            user_num.add(0,tmp_num);
            num /= 10;
        }
        return user_num;
    }

    public static boolean chk_is_win(int[] try_result){
        boolean flag = false;
        int cnt = 0;
        for(int i=0; i<3; i++){
            if(try_result[i]==0){ // 스트라이크인지 검사
                cnt+=1;
            }
        }
        if(cnt==3){
            return true; //이김
        }
        else{
            return flag;
        }
    }

    public static void print_result(int[] try_result){
        int cnt_ball=try_result[0]; int cnt_strike=try_result[1]; int if_nothing =try_result[2];
        if(if_nothing==1){
            System.out.println("낫싱");
            return;
        }
        else if(cnt_ball==0&&cnt_strike==0&&if_nothing==0){
            return;
        }
        System.out.println(cnt_ball+"볼 "+cnt_strike+"스트라이크");
    }
    public static void game(){
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> computer_num = make_computer_num(); // ArrayList에 랜덤한 컴퓨터의 숫자 담기
        boolean flag= false; // 게임 승패유무 판단

        while(!flag){ //게임 끝날 때 까지
            System.out.println("숫자를 입력해주세요 : ");
            int num = scanner.nextInt(); //user의 숫자 입력받기
            ArrayList<Integer> user_num = make_user_num(num); //user의 숫자 ArrayList로 만들기
            int[] try_result = judge(user_num, computer_num); // try_result 에 결과 담기
            flag = chk_is_win(try_result); // win => flag= true
            print_result(try_result); // 게임결과 출력하기
        } // 이기면 loop 탈출
        System.out.println("3스트라이크");
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        int start_end = scanner.nextInt();
        if(start_end==1){ //1 입력시 리겜
            game();
        }
        else if(start_end!=2){ // 1도아니고 2도아니면 오류띄우기
            throw new IllegalArgumentException();
        }
    }
    public static void main(String[] args){
        System.out.println("숫자 야구 게임을 시작합니다.");
        game();
    }
}
