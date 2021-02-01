import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-09-08 15:24
 */
public class FormatTest {
    public void format(String pattern, double value) {
        DecimalFormat df = new DecimalFormat(pattern);
        String str = df.format(value);
        System.out.println("使用" + pattern + "\t格式化数字" + value + "：\t" + str);
    }

    public static void main(String[] args) {
        FormatTest demo = new FormatTest();
        demo.format("###,###.###", 111222.34567);
        demo.format("000,000.000", 11222.34567);
        demo.format("###,###.###$", 111222.34567);
        demo.format("000,000.000￥", 11222.34567);
        demo.format("#.##%", 0.345678);        // 使用百分数形式
        demo.format("00.###%", 0.0345678);        // 使用百分数形式
        demo.format("###.###\u2030", 0.345678);    // 使用千分数形式

        List<String> list = new ArrayList<>();
        list.add("241245");
        list.add("241243");
        list.add("241241");
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(x -> stringBuilder.append(x).append(","));
        System.out.println(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
    }
}
