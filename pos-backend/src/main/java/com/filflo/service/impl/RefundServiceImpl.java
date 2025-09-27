package com.filflo.service.impl;

import com.filflo.domain.OrderStatus;
import com.filflo.exception.ResourceNotFoundException;
import com.filflo.exception.UserException;
import com.filflo.modal.Branch;
import com.filflo.modal.Order;
import com.filflo.modal.Refund;
import com.filflo.modal.User;
import com.filflo.payload.dto.RefundDTO;
import com.filflo.repository.BranchRepository;
import com.filflo.repository.OrderRepository;
import com.filflo.repository.RefundRepository;
import com.filflo.service.RefundService;
import com.filflo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BranchRepository branchRepository;

    @Override
    @Transactional
    public Refund createRefund(RefundDTO refundDTO) throws UserException, ResourceNotFoundException {
        User currentCashier = userService.getCurrentUser();

        Order order = orderRepository.findById(refundDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Branch branch=branchRepository.findById(refundDTO.getBranchId()).orElseThrow(
                ()-> new EntityNotFoundException("branch not found")
        );

        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setCashier(currentCashier);
        refund.setReason(refundDTO.getReason());
        refund.setAmount(order.getTotalAmount());
        refund.setCreatedAt(LocalDateTime.now());
        refund.setBranch(branch);


        Refund savedRefund=refundRepository.save(refund);
        order.setStatus(OrderStatus.REFUNDED);
        orderRepository.save(order);
        return savedRefund;
    }

    @Override
    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    @Override
    public List<Refund> getRefundsByCashier(Long cashierId) {
        return refundRepository.findByCashierId(cashierId);
    }

    @Override
    public List<Refund> getRefundsByShiftReport(Long shiftReportId) {
        return refundRepository.findByShiftReportId(shiftReportId);
    }

    @Override
    public List<Refund> getRefundsByCashierAndDateRange(Long cashierId, LocalDateTime from, LocalDateTime to) {
        return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, from, to);
    }

    @Override
    public List<Refund> getRefundsByBranch(Long branchId) {
        List<Refund> refunds= refundRepository.findByBranchId(branchId);
        return refunds;
    }

    @Override
    public Refund getRefundById(Long id) throws ResourceNotFoundException {
        return refundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Refund not found"));
    }

    @Override
    public void deleteRefund(Long refundId) throws ResourceNotFoundException {
        if (!refundRepository.existsById(refundId)) {
            throw new ResourceNotFoundException("Refund not found");
        }
        refundRepository.deleteById(refundId);
    }


}
