package com.supplychain.backend.service;

import com.supplychain.backend.dto.OrderRequest;
import com.supplychain.backend.dto.OrderResponse;
import com.supplychain.backend.exception.BadRequestException;
import com.supplychain.backend.exception.ResourceNotFoundException;
import com.supplychain.backend.model.Inventory;
import com.supplychain.backend.model.Order;
import com.supplychain.backend.repository.InventoryRepository;
import com.supplychain.backend.repository.OrderRepository;
import com.supplychain.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public OrderResponse createOrder(OrderRequest request) {
        if (!productRepository.existsById(request.getProductId())) {
            throw new ResourceNotFoundException("Product not found with id: " + request.getProductId());
        }

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(request.getProductId(), request.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for product and warehouse"));

        if (inventory.getStockLevel() < request.getQuantity()) {
            throw new BadRequestException("Insufficient stock available");
        }

        inventory.setStockLevel(inventory.getStockLevel() - request.getQuantity());
        inventoryRepository.save(inventory);

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setOrderDate(request.getOrderDate());
        order.setDeliveryDate(request.getDeliveryDate());
        order.setWarehouseId(request.getWarehouseId());
        order.setOrderStatus(request.getOrderStatus());

        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return mapToResponse(order);
    }

    public List<OrderResponse> getOrdersByProductId(String productId) {
        return orderRepository.findByProductId(productId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OrderResponse> getOrdersByWarehouseId(Integer warehouseId) {
        return orderRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OrderResponse> getOrdersByStatus(String orderStatus) {
        return orderRepository.findByOrderStatus(orderStatus)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse updateOrderStatus(String id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        order.setOrderStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(updatedOrder);
    }

    public void deleteOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getQuantity(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getWarehouseId(),
                order.getOrderStatus()
        );
    }
}