package main;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author chenfh
 * @date 2020-03-10 10:40
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String,Object> listMap1 = new HashMap<>();
        listMap1.put("12","mip");
        listMap1.put("13","mio");
        listMap1.put("14","miq");

        List<Map> list = new LinkedList<>();
        list.add(listMap1);

        HashMap<String, Object> sub = new HashMap<String, Object>();
        sub.put("12","q");
        sub.put("13","w");
        sub.put("14","e");

        HashMap<String, Object> maps = new HashMap<String, Object>();
        maps.put("10", "AA");
        maps.put("11", "BB");
        maps.put("12", "CC");
        maps.put("13", "DD");
        maps.put("14",sub);
        maps.put("15",list);

        del(maps);

        System.out.println(maps);
    }


    public static HashMap del(HashMap origin) {
        for (Iterator<Map.Entry<String, Object>> it = origin.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> item = it.next();
            if (item.getKey().equals("12")){
                it.remove();
            } else if (item.getValue() instanceof HashMap) {
                del((HashMap) item.getValue());
            } else if (item.getValue() instanceof List) {
                Iterator<Object> iterator = ((List)item.getValue()).iterator();
                while (iterator.hasNext()) {
                    Object sub = iterator.next();
                    if (sub instanceof HashMap) {
                        del((HashMap) sub);
                    }
                }
            }
        }
        return origin;
    }
}
