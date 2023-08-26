package com.example.demo.service;
import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.dto.OrderItemRequestDto;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public Order placeOrder(List<OrderItemRequestDto> orderItems) {

        List<Product> products = new ArrayList<>();

        for (OrderItemRequestDto orderItem : orderItems) {
            Product product = productRepository.findById(orderItem.getProductId()).orElse(null);
            if (product == null) {
                // Handle product not found
                return null;
            }
            products.add(product);
        }
        float totalPrice = calculateTotalPrice(orderItems, products);
        Order order = new Order();
        order.setProducts(products);
        order.setOrderDate(new Date());
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    private float calculateTotalPrice(List<OrderItemRequestDto> orderItems, List<Product> products) {
        float totalPrice = 0.0f;
        for (OrderItemRequestDto orderItem : orderItems) {
            for (Product product : products) {
                if (orderItem.getProductId().equals(product.getId())) {
                    totalPrice += product.getPrice() * orderItem.getQuantity();
                    break;
                }
            }
        }
        return totalPrice;
    }
}
