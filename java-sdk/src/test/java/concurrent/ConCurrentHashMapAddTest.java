package concurrent;

import org.springframework.util.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author chenfh
 * @date 2020-10-23 13:55
 */
public class ConCurrentHashMapAddTest {
    private static int LOOP_COUNT = 1000000;
    private static int THREAD_COUNT = 10;
    //元素数量
    private static int ITEM_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normal");
        Map<String, Long> normal = normaluse();
        stopWatch.stop();
        System.out.println("normal size:" + normal.size());
        System.out.println("normal sum:"+normal.entrySet().stream().mapToLong(x->x.getValue()).reduce(0,Long::sum));

        stopWatch.start("goodUse");
        Map<String, Long> goodUse = gooduse();
        stopWatch.stop();
        System.out.println("goodUse size:" + goodUse.size());
        System.out.println("goodUse size:"+goodUse.entrySet().stream().mapToLong(x->x.getValue()).reduce(0,Long::sum));

        System.out.println(stopWatch.prettyPrint());

    }


    public static Map<String, Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(x -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            concurrentHashMap.computeIfAbsent(key,k->new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return concurrentHashMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e->e.getValue().longValue()));
    }

    public static Map<String, Long> normaluse() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(x -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            synchronized (concurrentHashMap) {
                if (concurrentHashMap.containsKey(key)) {
                    concurrentHashMap.put(key, concurrentHashMap.get(key) + 1);
                } else {
                    concurrentHashMap.put(key, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return concurrentHashMap;
    }
}
