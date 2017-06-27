import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Класс для ведения статистика запусков GC
 */
public class WriterStatistic implements Runnable {

    private static final int NANO_SECOND_IN_MILLISECOND = 1_000_000;
    private static final int MILLISECOND_IN_SECOND = 1_000;

    public static final int intervalStatistic = 30000;

    private long lastWriteStatistic;

    private long startTimeThread;

    public void writeStatistic() {
        long workTimeApp = getWorkTimeThread();
        //Пишем статистику на консоль каждые  intervalStatistic ms
        boolean isWriteStatistic = (workTimeApp - lastWriteStatistic) > intervalStatistic;
        if (isWriteStatistic) {
            System.out.println("---GC statistic---");
            //Список всей собранной инфы по всем запущененым GC
            List<GarbageCollectionInfo> gcInfoList = MemoryMeasurement.getGcInfoList();
            //Список инфы по GC запущенных за последний период времяни
            List<GarbageCollectionInfo> launchedGCsInInterval = gcInfoList.stream().filter(k -> k.getStartTime() > lastWriteStatistic && k.getStartTime() < workTimeApp).collect(Collectors.toList());
            System.out.println(String.format("Last %s s, launched %s GC",intervalStatistic / MILLISECOND_IN_SECOND, launchedGCsInInterval.size()));
            //key - тип GC, value - список инфы запущенных GC
            Map<GCType, List<GarbageCollectionInfo>> groupTypesGC = launchedGCsInInterval.stream().collect(Collectors.groupingBy(GarbageCollectionInfo::getGCTypeByName, Collectors.toList()));
            //Выводим инфу по типам GC.
            groupTypesGC.forEach((k,v) -> {
                long timeWorkGc = v.stream().mapToLong(GarbageCollectionInfo::getWorkTime).sum();
                System.out.println(String.format("Number of launches %s type  GC- %s, work time GC- %s ms", k, v.size(), timeWorkGc));
            });
            //выводим инфу за все время работы программы
            System.out.println(String.format("Work Time Application - %s s, All launched GC - %s",workTimeApp / MILLISECOND_IN_SECOND , gcInfoList.size()));
            Map<GCType, List<GarbageCollectionInfo>> groupTypesGCAll = gcInfoList.stream().collect(Collectors.groupingBy(k -> k.getGCTypeByName(), Collectors.toList()));
            groupTypesGCAll.forEach((k,v) -> {
                long timeWorkGc = v.stream().mapToLong(g -> g.getWorkTime()).sum();
                System.out.println(String.format("Number of launches %s type GC - %s, work time GC- %s ms", k, v.size(), timeWorkGc));
            });

            lastWriteStatistic = getWorkTimeThread();
        }
    }

    private long getWorkTimeThread() {
        long currentTime = System.nanoTime()/ NANO_SECOND_IN_MILLISECOND;
        return currentTime - startTimeThread;
    }

    @Override
    public void run() {
        startTimeThread = System.nanoTime() / NANO_SECOND_IN_MILLISECOND;
        while (true) {
            writeStatistic();
        }
    }
}
