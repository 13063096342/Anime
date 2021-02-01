package concurrent;

import java.util.Enumeration;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author chenfh
 * @date 2020-10-23 11:42
 */
public class ConCurrentHashMapTest {
    public static int THREAD_COUNT = 10;
    public static int ITEM_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, Long> concurrentHashMap = getData(900);
        //初始话900个元素
        System.out.println("init size:" + concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, THREAD_COUNT).parallel().forEach(x -> {
            //查询还需添加的元素
            synchronized (concurrentHashMap) {
                int remain = ITEM_COUNT - concurrentHashMap.size();
                System.out.println("remain size:" + remain);
                concurrentHashMap.putAll(getData(remain));
            }
        }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("final size:" + concurrentHashMap.size());
    }

    public static ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count).boxed().collect(Collectors.toConcurrentMap(x -> UUID.randomUUID().toString(), Function.identity(), (a, b) -> a, ConcurrentHashMap::new));
    }
}
