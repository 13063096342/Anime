import java.util.Arrays;
import java.util.HashMap;

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
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("list",Arrays.asList(1,2,3));
        System.out.println(hashMap.get("list"));
    }
}
