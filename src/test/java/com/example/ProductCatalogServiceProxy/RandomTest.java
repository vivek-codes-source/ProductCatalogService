package com.example.ProductCatalogServiceProxy;

import org.junit.jupiter.api.Test;

import java.util.Random;



public class RandomTest {
    @Test
    public void TestRandomNumber() {
        Random random = new Random();
        int n = random.nextInt(10); // generates number between 0 and 9
        assert(n < 5);
    }
}
