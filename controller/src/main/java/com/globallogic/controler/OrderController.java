package com.globallogic.controler;

import com.globallogic.model.Order;
import com.globallogic.model.OrderHelper;
import com.globallogic.service.OrderService;
import com.globallogic.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class OrderController {
    // TODO: 9/12/2017 ExceptionHandler

    private OrderService orderService;

    @Autowired
    public OrderController(OrderServiceImpl orderService) {
        this.orderService=orderService;
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = "application/json")
     public ResponseEntity saveOrder(@RequestBody OrderHelper orderHelper) {
        if (orderService.saveOrder(orderHelper))
            return new ResponseEntity(HttpStatus.OK);
         else
            return new ResponseEntity<>("Please check if unique id of order and animals are correct", HttpStatus.CONFLICT);
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = "application/json")
    public Order getOrder(@PathVariable String orderId) {
        return orderService.readOrder(Integer.valueOf(orderId));
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET, produces = "application/json")
    public Map<Integer, Order> getOrders() {
        return orderService.showAllOrders();
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(Integer.valueOf(orderId));
    }

    @RequestMapping(value = "/order-txt", method = RequestMethod.POST)
    public void exportOrdersToTxt() {
        orderService.saveDataToTxtFile();
    }

}