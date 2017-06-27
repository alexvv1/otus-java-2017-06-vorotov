/**
 * Created by SSS on 24.06.2017.
 */
public class AppRunner {
    public static void main(String[] args) throws InterruptedException {

        MemoryMeasurement measurement = new MemoryMeasurement();
        measurement.run();

        WriterStatistic myRunnable = new WriterStatistic();
        Thread myThread = new Thread(myRunnable);
        myThread.start();


        MemoryLeak memoryLeak = new MemoryLeak();
        memoryLeak.setSize(10000);
        memoryLeak.getMemoryLeak( ()-> new String());
    }

/*
Результат выполнения:
---GC statistic---
Last 30 s, launched 0 GC
Work Time Application - 30 s, All launched GC - 0
-----GC_Start------
end of minor GC: - 1, PS Scavenge (from Allocation Failure) 27 milliseconds
-----GC_END------
---GC statistic---
Last 30 s, launched 1 GC
Number of launches YOUNG type  GC- 1, work time GC- 27 ms
Work Time Application - 60 s, All launched GC - 1
Number of launches YOUNG type GC - 1, work time - 27 ms
---GC statistic---
Last 30 s, launched 0 GC
Work Time Application - 90 s, All launched GC - 1
Number of launches YOUNG type GC - 1, work time - 27 ms
---GC statistic---
Last 30 s, launched 0 GC
Work Time Application - 120 s, All launched GC - 1
Number of launches YOUNG type GC - 1, work time - 27 ms
---GC statistic---
Last 30 s, launched 0 GC
Work Time Application - 150 s, All launched GC - 1
Number of launches YOUNG type GC - 1, work time - 27 ms
-----GC_Start------
end of minor GC: - 2, PS Scavenge (from Allocation Failure) 192 milliseconds
-----GC_END------
-----GC_Start------
end of minor GC: - 3, PS Scavenge (from Allocation Failure) 152 milliseconds
-----GC_END------
-----GC_Start------
end of major GC: - 1, PS MarkSweep (from Allocation Failure) 1148 milliseconds
-----GC_END------
---GC statistic---
Last 30 s, launched 3 GC
Number of launches YOUNG type  GC- 2, work time GC- 344 ms
Number of launches OLD type  GC- 1, work time GC- 1148 ms
Work Time Application - 180 s, All launched GC - 4
Number of launches YOUNG type GC - 3, work time - 371 ms
Number of launches OLD type GC - 1, work time - 1148 ms
---GC statistic---
Last 30 s, launched 0 GC
Work Time Application - 210 s, All launched GC - 4
Number of launches YOUNG type GC - 3, work time - 371 ms
Number of launches OLD type GC - 1, work time - 1148 ms
-----GC_Start------
end of major GC: - 2, PS MarkSweep (from Ergonomics) 1074 milliseconds
-----GC_END------
-----GC_Start------
end of minor GC: - 4, PS Scavenge (from Allocation Failure) 1 milliseconds
-----GC_END------
-----GC_Start------
end of major GC: - 3, PS MarkSweep (from Allocation Failure) 4508 milliseconds
-----GC_END------
---GC statistic---
Last 30 s, launched 3 GC
Number of launches YOUNG type  GC- 1, work time GC- 1 ms
Number of launches OLD type  GC- 2, work time GC- 5582 ms
Work Time Application - 243 s, All launched GC - 7
Number of launches YOUNG type GC - 4, work time - 372 ms
Number of launches OLD type GC - 3, work time - 6730 ms
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at MemoryLeak.getMemoryLeak(MemoryLeak.java:20)
	at AppRunner.main(AppRunner.java:17)

Process finished with exit code 1

 */
}
