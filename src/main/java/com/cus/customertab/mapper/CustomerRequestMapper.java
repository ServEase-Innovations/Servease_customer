package com.cus.customertab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cus.customertab.dto.CustomerRequestDTO;
import com.cus.customertab.entity.CustomerRequest;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    CustomerRequestMapper INSTANCE = Mappers.getMapper(CustomerRequestMapper.class);

    @Mapping(source = "customer.customerId", target = "customerId")
    CustomerRequestDTO customerRequestToDTO(CustomerRequest customerRequest);

    @Mapping(source = "customerId", target = "customer.customerId")
    CustomerRequest dtoToCustomerRequest(CustomerRequestDTO customerRequestDTO);
}