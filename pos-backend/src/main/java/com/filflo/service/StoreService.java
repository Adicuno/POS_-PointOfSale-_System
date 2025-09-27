package com.filflo.service;


import com.filflo.domain.StoreStatus;
import com.filflo.exception.ResourceNotFoundException;
import com.filflo.exception.UserException;
import com.filflo.modal.Store;
import com.filflo.modal.User;
import com.filflo.payload.dto.StoreDTO;
import com.filflo.payload.dto.UserDTO;

import java.util.List;

public interface StoreService {
    StoreDTO createStore(StoreDTO storeDto, User user);
    StoreDTO getStoreById(Long id) throws ResourceNotFoundException;
    List<StoreDTO> getAllStores(StoreStatus status);
    Store getStoreByAdminId() throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;
    StoreDTO updateStore(Long id, StoreDTO storeDto) throws ResourceNotFoundException, UserException;
    void deleteStore() throws ResourceNotFoundException, UserException;
    UserDTO addEmployee(Long id, UserDTO userDto) throws UserException;
    List<UserDTO> getEmployeesByStore(Long storeId) throws UserException;

    StoreDTO moderateStore(Long storeId, StoreStatus action) throws ResourceNotFoundException;

}

