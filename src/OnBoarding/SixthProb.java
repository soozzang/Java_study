package OnBoarding;
import java.util.*;

public class SixthProb {
    public static void main(String[] args){
        String[][] forms = {
                {"jm@email.com", "제이엠"},
                {"jason@email.com", "제이슨"},
                {"woniee@email.com", "워니"},
                {"mj@email.com", "엠제이"},
                {"nowm@email.com", "이제엠"}
        };

        Set<String> sameValues = new HashSet<>();
        ArrayList<String> user_emails = new ArrayList<>();

        for (int i = 0; i < forms.length; i++) {
            for (int j = 0; j < forms[i][1].length() - 1; j++) {
                String new_str = forms[i][1].substring(j, j + 2);
                if (!sameValues.add(new_str)) {
                    user_emails.add(forms[i][0]);
                    for(int k=0;k<i;k++){
                        if(forms[k][1].contains(new_str)) {
                            if(!user_emails.contains(forms[k][0]))
                                user_emails.add(forms[k][0]);
                        }
                    }
                }
            }
        }

        String[] arr = user_emails.toArray(new String[user_emails.size()]);
        Arrays.sort(arr);

        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
