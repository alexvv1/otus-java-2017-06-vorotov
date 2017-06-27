import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by SSS on 24.06.2017.
 */
public class MemoryLeak {

    private int countCreateObj;

    public void getMemoryLeak(Supplier<Object> obj) throws InterruptedException {
                Runtime runtime = Runtime.getRuntime();
        List<Object> objects = new ArrayList<>();
        while (true) {
            Thread.sleep(50);
            for (int i = 0; i < countCreateObj; i++) {
                objects.add(obj);
            }
            int countRemoveObj = countCreateObj;
            for (int i = 0; i < countRemoveObj / 3; i++) {
                int j = objects.size() - 1;
                objects.remove(j);
            }
        }
    }

    public int getSize() {
        return countCreateObj;
    }

    public void setSize(int countCreateObj) {
        this.countCreateObj = countCreateObj;
    }


}
