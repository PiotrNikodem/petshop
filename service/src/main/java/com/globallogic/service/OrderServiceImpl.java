package com.globallogic.service;

import com.globallogic.dao.AnimalDao;
import com.globallogic.dao.AnimalDaoImpl;
import com.globallogic.dao.OrderDao;
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


    private OrderDao orderDao;
    private AnimalDao animalDao;

    @Autowired
    public OrderServiceImpl(OrderDaoImpl orderDao, AnimalDaoImpl animalDao) {
        this.orderDao=orderDao;
        this.animalDao=animalDao;
    }

    @Override
    public Order readOrder(int id) {
        return orderDao.readOrder(id);
    }

    @Override
    public boolean saveOrder(OrderHelper orderHelper) {
        if (orderDao.showAllOrders().containsKey(orderHelper.getId()))
            return false;

        List<Animal> animals = Arrays.stream(orderHelper.getAnimalIds()).mapToObj(s-> animalDao.getAnimal(s)).collect(Collectors.toList());
        Order order = new Order.Builder(orderHelper.getId(), orderHelper.getName()).
                    withTodayDate().addAnimal(animals).comment(orderHelper.getComment()).build();
        orderDao.saveOrder(order);
        return true;
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
