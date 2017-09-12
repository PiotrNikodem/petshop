package com.globallogic.dao;

import com.globallogic.model.Order;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;

@Component
public class OrderDaoImpl implements OrderDao {

    private static final String FILE_PATH_SERIALIZATION = "c:/projects/orders.ser";
    private static final String FILE_PATH_MANAGER_TXT = "c:/projects/orders.txt";
    private Map<Integer, Order> orders = new HashMap();


    @Override
    public Order readOrder(int id) {
        return orders.get(id);
    }

    @Override
    public boolean saveOrder(Order order) {
        if (orders.containsKey(order.getId()))
                return false;
        orders.put(order.getId(), order);
        saveDataToFile();
        return true;
    }

    @Override
    public Map<Integer, Order> showAllOrders() {
        readDataFromFile();
        return orders;
    }

    @Override
    public void deleteOrder(int id) {
        orders.remove(id);
        saveDataToFile();
    }

    @Override
    public void saveDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH_SERIALIZATION))) {
            oos.writeObject(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH_SERIALIZATION))) {
            orders = (HashMap) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDataToTxtFile() {
        try (FileWriter fw = new FileWriter(FILE_PATH_MANAGER_TXT, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
             orders.entrySet().forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}