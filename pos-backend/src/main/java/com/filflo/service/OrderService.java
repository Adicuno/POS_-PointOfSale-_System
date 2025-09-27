package com.filflo.service;


import com.filflo.domain.OrderStatus;
import com.filflo.domain.PaymentType;
import com.filflo.exception.UserException;
import com.filflo.payload.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO dto) throws UserException;
    OrderDTO getOrderById(Long id);

    List<OrderDTO> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status);
    List<OrderDTO> getOrdersByCashier(Long cashierId);
    void deleteOrder(Long id);
    List<OrderDTO> getTodayOrdersByBranch(Long branchId);
    List<OrderDTO> getOrdersByCustomerId(Long customerId);
    List<OrderDTO> getTop5RecentOrdersByBranchId(Long branchId);
}
