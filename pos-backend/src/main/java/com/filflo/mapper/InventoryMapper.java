package com.filflo.mapper;


import com.filflo.modal.Branch;
import com.filflo.modal.Inventory;
import com.filflo.modal.Product;
import com.filflo.payload.dto.InventoryDTO;

public class InventoryMapper {

    public static InventoryDTO toDto(Inventory inventory) {
        return InventoryDTO.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .quantity(inventory.getQuantity())
                .build();
    }

    public static Inventory toEntity(InventoryDTO dto, Branch branch, Product product) {
        return Inventory.builder()
                .id(dto.getId())
                .branch(branch)
                .product(product)
                .quantity(dto.getQuantity())
                .build();
    }
}

