package OnBoarding;
import java.lang.reflect.Array;
import java.util.*;

//추천 친구가 0점=>추천x / 추천 점수가 같은 경우=> 이름순
public class SeventhProb {

    public static String[] solution(String user, String[][] friends, String[] visitors){
        HashMap<String,ArrayList<String>>map=make_map(friends); // 친구관계를 해시맵으로 만듬
        HashSet<String> user_friend = find_friend(user, map); // user와 친구인 사람들만 해시셋에 넣음
        HashMap<String, Integer> know_same_friends = count_friends(user,map,user_friend); // <친구의 친구인 사람, 그 수>

        for (Map.Entry<String, Integer> entry : know_same_friends.entrySet()) {
            String friend = entry.getKey();
            int count = entry.getValue();

            know_same_friends.put(friend, count * 10);
        } // 함께 아는 친구수 *=10 해줌 (추천점수)

        HashMap<String,Integer> friends_map = count_visitors(visitors,know_same_friends,user_friend); // 방문자 점수 +1씩 추가
        List<String> keySet = new ArrayList<>(friends_map.keySet()); // 점수 높은 순으로 내림차순 정렬
        keySet.sort((o1, o2) -> friends_map.get(o2).compareTo(friends_map.get(o1)));

        keySet.sort(
                (o1, o2) -> {
                    int scoreCompare = friends_map.get(o2).compareTo(friends_map.get(o1));
                    if (scoreCompare == 0) {
                        return o1.compareTo(o2); // 알파벳 순서 비교
                    }
                    return scoreCompare;
                }
        );

        int cnt = Math.min(5, keySet.size()); //최대 5개 뽑기
        String[] rank_friends = new String[cnt];

        for (int i=0; i< cnt; i++){
            rank_friends[i] = keySet.get(i);
        }
        return rank_friends;
    }

    //visitors 안에 든 사람중, 내 친구가 아닌사람들만 점수 +1추가하는 함수
    public static HashMap<String, Integer> count_visitors(String[] visitors, HashMap<String,Integer> know_same_friends, HashSet<String> user_friend) {
        for (String visitor : visitors) {
            if (know_same_friends.containsKey(visitor)) {
                int cnt = know_same_friends.get(visitor);
                know_same_friends.put(visitor, cnt + 1);
            } else if (!know_same_friends.containsKey(visitor) && !user_friend.contains(visitor)) {
                know_same_friends.put(visitor, 1);
            }
        }
        return know_same_friends;
    }

    //같이 아는 친구가 몇명인지 카운트해서 HashMap<String, Integer> 의 형태로 return 하는 함수
    public static HashMap<String, Integer> count_friends(String user, HashMap<String,ArrayList<String>> map, HashSet<String>user_friend){
        HashMap<String,Integer> know_same_friends = new HashMap<>();

        for(Map.Entry<String, ArrayList<String>> i : map.entrySet()) {
            String key = i.getKey();
            ArrayList<String> value = i.getValue();

            for (String friend : value){ // value 중에서 친구 탐색
                if (user_friend.contains(friend)) { // 내친구 목록에서, 전체친구중 찾은 value(친구)가 있다면=> 해당key는 내친구를 동시에 아는친구임
                    if (!user_friend.contains(key) && !user.equals(key))
                        if (know_same_friends.containsKey(key)) {
                            int cnt = know_same_friends.get(key);
                            know_same_friends.put(key, cnt + 1);
                        } else {
                            know_same_friends.put(key, 1);
                        }
                }
            }

            if(user_friend.contains(key)){
                for (String friend : value){
                    if(!user_friend.contains(friend)&& !user.equals(friend)){
                        if (know_same_friends.containsKey(friend)) {
                            int cnt = know_same_friends.get(friend);
                            know_same_friends.put(friend, cnt + 1);
                        } else {
                            know_same_friends.put(friend, 1);
                        }
                    }
                }
            }
        }
        return know_same_friends;
    }

    // user의 친구만 HashSet에 담기
    public static HashSet<String> find_friend(String user, HashMap<String,ArrayList<String>>map){
        HashSet<String> set = new HashSet<String>();
        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            if(key.equals(user)){
                for(String i : value){
                    set.add(i);
                }
            }
            else if(value.contains(user)){
                set.add(key);
            }
        }
        return set;
    }

    //Hashmap에 문제에서 주어지는 친구관계를 넣기

    public static HashMap<String,ArrayList<String>> make_map(String[][] friends){
        HashMap<String, ArrayList<String>> map = new HashMap();
        for(String[] i : friends){
            String key = i[0];
            String value = i[1];
            //이미 해당 key가 존재하는 경우, list에 추가
            if (map.containsKey(key)){
                map.get(key).add(value);
            }
            else {
                ArrayList<String> values = new ArrayList<>();
                values.add(value);
                map.put(key,values);
            }
        }
        return map;
    }

    //main
    public static void main(String[] args){
        String user = "mrko";

        String[][] friends = {
                {"donut", "andole"},
                {"donut", "jun"},
                {"donut", "mrko"},
                {"shakevan", "andole"},
                {"shakevan", "jun"},
                {"shakevan", "mrko"}
        };
        String[] visitors = {"bedi", "bedi", "donut", "bedi", "shakevan"};

        String[] result = solution(user,friends,visitors);
        for(String i:result){
            System.out.println(i);
        }
    }
}
