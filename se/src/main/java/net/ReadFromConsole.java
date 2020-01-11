import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadFromConsole {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!"bye".equals(s = reader.readLine())) {
            System.out.println("收到信息：" + s);
        }
        System.out.println("退出程序");
    }
}
