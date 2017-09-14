package com.globallogic.service;

import com.globallogic.dao.AnimalDaoImpl;
import com.globallogic.dao.OrderDaoImpl;
import com.globallogic.model.Order;
import com.globallogic.model.OrderHelper;
import com.globallogic.model.animals.Hamster;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    OrderDaoImpl orderDao;

    @Mock
    AnimalDaoImpl animalDao;

    @InjectMocks
    OrderServiceImpl service;

    @Captor
    ArgumentCaptor<Order> captor;

    private Hamster hamster;
    private OrderHelper orderHelper;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        hamster = new Hamster("elo", new BigDecimal(10), new Date(12321312L));
        hamster.setId(4);

        orderHelper = new OrderHelper();
        int[] ids = {4};
        orderHelper.setId(10);
        orderHelper.setAnimalIds(ids);
        orderHelper.setName("elo");
    }

    @AfterMethod
    public void reset() {
        Mockito.reset(orderDao);
    }
    @Test
    public void saveOrderValid() {
        when(animalDao.getAnimal(4)).thenReturn(hamster);
        boolean isTrue = service.saveOrder(orderHelper);
        verify((orderDao), times(1)).saveOrder(captor.capture());
        verify((orderDao), times(1)).saveOrder(captor.capture());

        Assert.assertEquals(captor.getValue().getId(), orderHelper.getId());
        Assert.assertEquals(captor.getValue().getName(), orderHelper.getName());
        Assert.assertNotNull(captor.getValue().getDate());
        Assert.assertTrue(isTrue);
    }

    @Test
    public void saveOrderWithExistingId() {
        Map<Integer, Order> map = new HashMap<>();
        map.put(10, new Order.Builder(10, "").build());
        when(animalDao.getAnimal(4)).thenReturn(hamster);
        when(orderDao.showAllOrders()).thenReturn(map);

        boolean isFalse = service.saveOrder(orderHelper);

        verify((orderDao), times(0)).saveOrder(any(Order.class));
        Assert.assertFalse(isFalse);
    }

}
