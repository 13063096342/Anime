import com.java.sdk.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenfh
 * @date 2020-09-27 09:50
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer>  listA = new ArrayList<>();
        listA.add(1);
        listA.add(2);
        listA.add(3);
        listA.add(4);

        List<Integer>  listB = new ArrayList<>();
        listB.add(1);
        listB.add(2);

        listA.removeAll(listB);

        System.out.println(listA);


        List<Person> personListA = new ArrayList<>();
        personListA.add(new Person("1",11));
        personListA.add(new Person("2",22));
        personListA.add(new Person("3",33));
        personListA.add(new Person("4",44));
    }
}
