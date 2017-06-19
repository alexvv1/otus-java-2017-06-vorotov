import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void addAllTest() {
        //Arrange
        List<String> myList = new MyArrayList<>();
        //Act
        String[] arrElem = new String[]{"elem1", "elem2", "elem3"};
        Collections.addAll(myList, arrElem);
        //Assert
        Iterator<String> iterator = myList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            Assert.assertEquals(arrElem[i], next);
            i++;
        }
    }

    @Test
    public void copyTest() {
        //Arrange
        List<String> destArr = new MyArrayList<>();
        //Необходимо выравнять размер массивов перед Collections.copy
        destArr.add(null);
        destArr.add(null);
        destArr.add(null);

        List<String> srcArr = new MyArrayList<>();
        String[] arrElem = new String[]{"elem1", "elem2", "elem3"};
        Collections.addAll(srcArr, arrElem);
        //Act
        Collections.copy(destArr, srcArr);
        //Assert
        Iterator<String> iterator = destArr.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            Assert.assertEquals(arrElem[i], next);
            i++;
        }
    }

    @Test
    public void sortTest() {
        //Arrange
        List<String> actualList = new MyArrayList<>();
        String[] sourceArr = new String[]{"elem3", "elem1", "elem2"};
        Collections.addAll(actualList, sourceArr);
        //Act
        Collections.sort(actualList, new StringComparator());
        //Assert
        Iterator<String> iterator = actualList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            String next = iterator.next();
            String[] expectedArr = new String[]{"elem1", "elem2", "elem3"};
            Assert.assertEquals(expectedArr[i], next);
            i++;
        }

    }

    private class StringComparator  implements Comparator<String> {
        public int compare(String obj1, String obj2) {
            if (obj1 == obj2) {
                return 0;
            }
            if (obj1 == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            return obj1.compareTo(obj2);
        }
    }


}