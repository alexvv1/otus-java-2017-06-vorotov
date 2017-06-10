package ru.otus.L2;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MemoryMeter memoryMeter = new MemoryMeter();
        long objSize;

        objSize = memoryMeter.getMemoryObject(() -> new Object());
        System.out.println(String.format("Object = %s bytes", objSize));

        objSize = memoryMeter.getMemoryObject(() -> new String());
        System.out.println(String.format("String = %s bytes", objSize));

        objSize = memoryMeter.getMemoryObject(() -> new int[1]);
        System.out.println(String.format("int[1] = %s bytes", objSize));

        objSize = memoryMeter.getMemoryObject(() -> new int[100]);
        System.out.println(String.format("int[100] = %s bytes", objSize));

        //Результаты выполнения
        //Object = 15 bytes
        //String = 23 bytes
        //int[1] = 24 bytes
        //int[100] = 416 bytes
    }
}
