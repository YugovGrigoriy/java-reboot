package ru.sberbank.edu;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CustomArrayImplTest {
    private CustomArrayImpl<Object> customArray;//переменная хранения объекта для проверок

    @BeforeEach
    public void setUp() {
        customArray = new CustomArrayImpl<>();
    }

    @Test
    public void testAddIncreasesSize() {
        String item = "test";
        int initialSize = customArray.size();
        customArray.add(item);
        int finalSize = customArray.size();
        Assertions.assertEquals(initialSize + 1, finalSize);
    }

    @Test
    public void testAddItem() {
        String item = "test";
        customArray.add(item);
        Object firstItem = customArray.get(0);
        Assertions.assertEquals(item, firstItem);
    }

    @Test
    public void testIsEmpty_WhenNoElementsAdded() {

        Assertions.assertTrue(customArray.isEmpty());
    }

    @Test
    public void testIsEmpty_AfterAddingElement() {
        customArray.add(1);
        Assertions.assertFalse(customArray.isEmpty());
    }

    @Test
    public void testAddAll_Array() {
        Integer[] elements = {1, 2, 3};
        customArray.addAll(elements);
        for (Integer element : elements) {
            Assertions.assertTrue(customArray.contains(element));
        }
    }

    @Test
    public void testRemove_Object() {
        customArray.add("Hello");
        boolean result = customArray.remove("Hello");
        Assertions.assertTrue(result);
        Assertions.assertTrue(customArray.isEmpty());
    }

    @Test
    public void testContains_WhenElementPresent() {
        customArray.add(1);
        Assertions.assertTrue(customArray.contains(1));
    }

    @Test
    public void testIndexOf_WhenElementPresent() {
        customArray.add(1);
        int index = customArray.indexOf(1);
        Assertions.assertEquals(0, index);
    }

    @Test
    public void testIndexOf() {
        Assertions.assertEquals(-1, customArray.indexOf(1));
    }

    @Test
    public void testIndexOf2() {
       customArray.add(1);
        int index = customArray.indexOf(1);
        Assertions.assertEquals(0, index);
    }

    @Test
    public void testIndexOf3() {
        Assertions.assertEquals(-1, customArray.indexOf(1));
    }

    @Test
    public void testReverse() {
        customArray.add(1);
        customArray.add(2);
        customArray.add(3);
        customArray.reverse();
        Integer[] expected = {3, 2, 1};
        Assertions.assertArrayEquals(expected, customArray.toArray());
    }

    @Test
    public void testToArray() {

        customArray.add(1);
        customArray.add(2);
        customArray.add(3);
        Object[] array = customArray.toArray();
        Assertions.assertArrayEquals(customArray.toArray(), array);
    }


    @AfterEach
    public void tearDown() {

        customArray = null;//todo вроде как после каждого выполнения тестового метода выполняется @AfterEach
    }//todo этот метод нужен мне для того, чтобы мой arrey перед каждым тестом точно был пустой, но возможно
    //todo @BeforeEach и так создает новый объект перед каждым вызовом тестового метода, хотелось бы вашу обратную связь по этому поводу.

}

