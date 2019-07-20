import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/19 22:04
 */
public class AA {
    public static void main(String[] args) {
        int aa = 185748625 % 2;
        System.out.printf(String.valueOf(aa));

        Collection<String> cc = new ArrayList<>();
        cc.add("a");
        cc.add("b");
        Object[] arr = cc.toArray();
        int c = -32496974 % 2;
        System.out.printf("a=" + arr[0] + ",b=" + arr[1] + ",c=" + c);
    }
}
