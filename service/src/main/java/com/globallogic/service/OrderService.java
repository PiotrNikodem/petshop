package com.globallogic.service;

import com.globallogic.model.Order;
import com.globallogic.model.OrderHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Order readOrder(int id);

    boolean saveOrder(OrderHelper orderHelper);

    Map<Integer, Order> showAllOrders();

    void deleteOrder(int id);

    void saveDataToTxtFile();
}
