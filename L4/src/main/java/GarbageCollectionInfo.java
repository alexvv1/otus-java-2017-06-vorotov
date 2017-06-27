/**
 * Created by SSS on 25.06.2017.
 */
public class GarbageCollectionInfo {

    public GarbageCollectionInfo(String gcName, long startTime, long workTime) {
        this.startTime = startTime;
        this.gcName = gcName;
        this.workTime = workTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public String getGcName() {
        return gcName;
    }

    public long getWorkTime() {
        return workTime;
    }

    public GCType getGCTypeByName() {
        return GCType.getGCTypeByName(gcName);
    }

    long startTime;

    String gcName;

    long workTime;

}

enum GCType {
    YOUNG, OLD, UNDEFINED;

    public static final String PS_SCAVENGE = "PS Scavenge";
    public static final String PS_MARK_SWEEP = "PS MarkSweep";

    public static GCType getGCTypeByName(String gcName) {
        switch (gcName) {
            case PS_SCAVENGE: {
                return YOUNG;
            }
            case PS_MARK_SWEEP: {
                return OLD;
            }

            default:
                return UNDEFINED;
        }
    }

}
