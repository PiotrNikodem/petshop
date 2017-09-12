package com.globallogic.service;

import com.globallogic.dao.AnimalDaoImpl;
import com.globallogic.dao.OrderDaoImpl;
import com.globallogic.model.Order;
import com.globallogic.model.OrderHelper;
import com.globallogic.model.animals.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDaoImpl orderDao;
    @Autowired
    AnimalDaoImpl animalDao;

    @Override
    public Order readOrder(int id) {
        return orderDao.readOrder(id);
    }

    @Override
    public boolean saveOrder(OrderHelper orderHelper) {
        List<Animal> animals = Arrays.stream(orderHelper.getAnimalIds()).mapToObj(s-> animalDao.getAnimal(s)).collect(Collectors.toList());
        Order order = new Order.Builder(orderHelper.getId(), orderHelper.getName()).
                    withTodayDate().addAnimal(animals).comment(orderHelper.getComment()).build();
        return orderDao.saveOrder(order);
    }

    @Override
    public Map<Integer, Order> showAllOrders() {
        return orderDao.showAllOrders();
    }

    @Override
    public void deleteOrder(int id) {
        orderDao.deleteOrder(id);
    }

    @Override
    public void saveDataToTxtFile() {
        orderDao.saveDataToTxtFile();
    }
}
