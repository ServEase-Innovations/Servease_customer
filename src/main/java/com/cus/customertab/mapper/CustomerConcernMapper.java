package com.cus.customertab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cus.customertab.dto.CustomerConcernDTO;
import com.cus.customertab.entity.CustomerConcern;

@Mapper(componentModel = "spring")
public interface CustomerConcernMapper {
    CustomerConcernMapper INSTANCE = Mappers.getMapper(CustomerConcernMapper.class);

    @Mapping(source = "customer.customerId", target = "customerId")
    CustomerConcernDTO customerConcernToDTO(CustomerConcern customerConcern);

    @Mapping(source = "customerId", target = "customer.customerId")
    CustomerConcern dtoToCustomerConcern(CustomerConcernDTO customerConcernDTO);
}
