package com.cus.customertab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.cus.customertab.dto.CustomerFeedbackDTO;
import com.cus.customertab.entity.CustomerFeedback;

@Mapper(componentModel = "spring")
public interface CustomerFeedbackMapper {
    CustomerFeedbackMapper INSTANCE = Mappers.getMapper(CustomerFeedbackMapper.class);

    CustomerFeedbackDTO customerFeedbackToDTO(CustomerFeedback customerFeedback); 
    CustomerFeedback dtoToCustomerFeedback(CustomerFeedbackDTO customerFeedbackDTO);
}