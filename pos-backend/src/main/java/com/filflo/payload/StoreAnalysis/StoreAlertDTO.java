package com.filflo.payload.StoreAnalysis;

import com.filflo.payload.dto.BranchDTO;
import com.filflo.payload.dto.ProductDTO;
import com.filflo.payload.dto.RefundDTO;
import com.filflo.payload.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreAlertDTO {
    private List<ProductDTO> lowStockAlerts;
    private List<BranchDTO> noSalesToday;
    private List<RefundDTO> refundSpikeAlerts;
    private List<UserDTO> inactiveCashiers;
}

