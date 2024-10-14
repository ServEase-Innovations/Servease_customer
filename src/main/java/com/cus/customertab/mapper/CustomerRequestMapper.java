package com.cus.customertab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cus.customertab.dto.CustomerRequestDTO;
import com.cus.customertab.entity.CustomerRequest;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {
    CustomerRequestMapper INSTANCE = Mappers.getMapper(CustomerRequestMapper.class);

    @Mapping(target = "comments", ignore = true) // Ignore the comments property
    CustomerRequestDTO customerRequestToDTO(CustomerRequest customerRequest);

    @Mapping(target = "comments", ignore = true) // Ignore the comments property for this mapping as well
    CustomerRequest dtoToCustomerRequest(CustomerRequestDTO customerRequestDTO);
}
