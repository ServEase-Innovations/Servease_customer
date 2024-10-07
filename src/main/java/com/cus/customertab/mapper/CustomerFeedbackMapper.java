package com.cus.customertab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.cus.customertab.dto.CustomerFeedbackDTO;
import com.cus.customertab.entity.CustomerFeedback;

@Mapper(componentModel = "spring")
public interface CustomerFeedbackMapper {
    CustomerFeedbackMapper INSTANCE = Mappers.getMapper(CustomerFeedbackMapper.class);

    @Mapping(source = "customer.customerId", target = "customerId")
    CustomerFeedbackDTO customerFeedbackToDTO(CustomerFeedback customerFeedback);

    @Mapping(source = "customerId", target = "customer.customerId") 
    CustomerFeedback dtoToCustomerFeedback(CustomerFeedbackDTO customerFeedbackDTO);
}