package com.filflo.service;


import com.filflo.exception.UserException;
import com.filflo.modal.User;
import com.filflo.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDto, User user);
    BranchDTO getBranchById(Long id);
    List<BranchDTO> getAllBranchesByStoreId(Long storeId) throws UserException;
    BranchDTO updateBranch(Long id, BranchDTO branchDto, User user) throws Exception;

    void deleteBranch(Long id);
}

