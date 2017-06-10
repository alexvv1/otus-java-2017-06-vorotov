package ru.otus.L2;

import java.util.function.Supplier;

public class MemoryMeter {

    private final int countCreateObj = 1_000_000;

    public long getMemoryObject(Supplier<Object> obj) throws InterruptedException {
        Object[] objects = new Object[countCreateObj];

        long startMemory = usedMemory();

        for (int i = 0; i < countCreateObj; i++) {
            objects[i] = obj.get();
        }

        long finishMemory = usedMemory();
        long sizeMemoryObject = (finishMemory - startMemory) / countCreateObj;

        for (int i = 0; i < countCreateObj; ++ i) objects [i] = null;
        return sizeMemoryObject;
    }

    private long usedMemory () throws InterruptedException {
        runGC();
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory () - runtime.freeMemory ();
    }

    private void runGC() throws InterruptedException {
        System.gc();
        Thread.sleep(100);
    }

}
