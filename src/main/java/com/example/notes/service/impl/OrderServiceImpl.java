package com.example.notes.service.impl;

import com.example.notes.enums.OrderStatusEnum;
import com.example.notes.enums.ResultEnum;
import com.example.notes.exception.MyException;
import com.example.notes.model.MangaInOrder;
import com.example.notes.model.MangaInfo;
import com.example.notes.model.OrderMain;
import com.example.notes.repository.MangaInOrderRepo;
import com.example.notes.repository.MangaInfoRepo;
import com.example.notes.repository.OrderRepo;
import com.example.notes.repository.UserRepo;
import com.example.notes.service.MangaService;
import com.example.notes.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    MangaInfoRepo mangaInfoRepo;
    @Autowired
    MangaService mangaService;
    @Autowired
    MangaInOrderRepo mangaInOrderRepo;

    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return orderRepo.findAllByOrderByOrderStatusAscCreateTimeDesc(pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepo.findAllByOrderStatusOrderByCreateTimeDesc(status, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepo.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeDesc(email, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepo.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeDesc(phone, pageable);
    }

    @Override
    public OrderMain findOne(Long orderId) {
        OrderMain orderMain = orderRepo.findByOrderId(orderId);
        if(orderMain == null) {
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public OrderMain finish(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepo.save(orderMain);
        return orderRepo.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public OrderMain cancel(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepo.save(orderMain);

        // Restore Stock
        Iterable<MangaInOrder> products = orderMain.getProducts();
        for(MangaInOrder mangaInOrder : products) {
            MangaInfo mangaInfo = mangaInfoRepo.findByProductId(mangaInOrder.getProductId());
            if(mangaInfo != null) {
                mangaService.increaseStock(mangaInOrder.getProductId(), mangaInOrder.getCount());
            }
        }
        return orderRepo.findByOrderId(orderId);

    }
}
