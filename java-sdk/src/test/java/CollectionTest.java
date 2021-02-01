import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author chenfh
 * @date 2020-08-17 12:13
 */
public class CollectionTest {
    public static void main(String[] args) {
        /*List<Person> personList = new ArrayList<>();
        personList.add(new Person().setName("mio").setAge(16));
        personList.add(new Person().setName("mio").setAge(17));
        personList.add(new Person().setName("mio").setAge(18));
        personList.add(new Person().setName("mio").setAge(19));
        personList.add(new Person().setName("Inory").setAge(19));
        personList.add(new Person().setName("Inory").setAge(18));
        personList.add(new Person().setName("Inory").setAge(17));

        Map<@NotNull String, List<Person>> collect = personList.stream().collect(Collectors.groupingBy(Person::getName));
        System.out.println(JSONObject.toJSONString(collect));*/
        /*HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("list",Arrays.asList(1,2,3));
        System.out.println(hashMap.get("list"));*/
        //线程个数
          int THREAD_COUNT = 10;
        //总元素数量
          int ITEM_COUNT = 10;

        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 1);
        System.out.println(concurrentHashMap.toString());
    }

    public static ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(), Function.identity(),
                        (o1, o2) -> o1, ConcurrentHashMap::new));
    }
}
