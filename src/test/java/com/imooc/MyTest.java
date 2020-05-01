package com.imooc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

    @Test
    public void aaa() {
        Gson gson = new Gson();

        String str = "[{productId:\"1\",productQuantity:\"1\"}]";

        List<OrderDetail> items = gson.fromJson(str, new TypeToken<List<OrderDetail>>(){}.getType());
        System.out.println(items.get(0).toString());
    }
}
