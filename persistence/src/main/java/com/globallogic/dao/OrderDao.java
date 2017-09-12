package com.globallogic.dao;

import com.globallogic.model.Order;
import java.util.Map;

public interface OrderDao {

    Order readOrder(int id);

    boolean saveOrder(Order order);

    Map<Integer, Order> showAllOrders();

    void deleteOrder(int id);

    void saveDataToFile();

    void readDataFromFile();

    void saveDataToTxtFile();

}
