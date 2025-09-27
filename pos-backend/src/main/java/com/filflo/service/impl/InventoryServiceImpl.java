package com.filflo.service.impl;


import com.filflo.exception.UserException;
import com.filflo.mapper.InventoryMapper;
import com.filflo.modal.Branch;
import com.filflo.modal.Inventory;
import com.filflo.modal.Product;
import com.filflo.payload.dto.InventoryDTO;
import com.filflo.repository.BranchRepository;
import com.filflo.repository.InventoryRepository;
import com.filflo.repository.ProductRepository;
import com.filflo.util.SecurityUtil;
import com.filflo.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    private final SecurityUtil securityUtil;

    @Override
    public InventoryDTO createInventory(InventoryDTO dto) throws AccessDeniedException, UserException {
        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

//        securityUtil.checkAuthority(branch);

        Inventory inventory = InventoryMapper.toEntity(dto, branch, product);
        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryDTO dto) throws AccessDeniedException, UserException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

//        securityUtil.checkAuthority(inventory);

        inventory.setQuantity(dto.getQuantity());
        return InventoryMapper.toDto(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(Long id) throws AccessDeniedException, UserException {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        securityUtil.checkAuthority(inventory);

        inventoryRepository.delete(inventory);
    }

    @Override
    public InventoryDTO getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));

        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDTO> getInventoryByBranch(Long branchId) {
        return inventoryRepository.findByBranchId(branchId)
                .stream()
                .map(InventoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDTO getInventoryByProductId(Long productId) {
        return InventoryMapper.toDto(inventoryRepository.findByProductId(productId));
    }
}

