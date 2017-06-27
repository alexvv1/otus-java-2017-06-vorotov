import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSS on 24.06.2017.
 */
public class MemoryMeasurement {


    public void run() {
        installGCMonitoring();
    }


    private static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            System.out.println(gcbean.getName());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    System.out.println("-----GC_Start------");

                    GarbageCollectionInfo gcInfo = new GarbageCollectionInfo(info.getGcName(),
                            info.getGcInfo().getStartTime(),
                            info.getGcInfo().getDuration());
                    gcInfoList.add(gcInfo);
                    long duration = info.getGcInfo().getDuration();
                    String gctype = info.getGcAction();

                    System.out.println(gctype + ": - "
                            + info.getGcInfo().getId() + ", "
                            + info.getGcName()
                            + " (from " + info.getGcCause() + ") " + duration + " milliseconds");
                    System.out.println("-----GC_END------");
                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }


    public static List<GarbageCollectionInfo> getGcInfoList() {
        return gcInfoList;
    }

    private static List<GarbageCollectionInfo> gcInfoList = new ArrayList<>();
}
