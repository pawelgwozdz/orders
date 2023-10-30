package com.qwist.orders.mapper;

import com.qwist.orders.dto.ItemParameterDto;
import com.qwist.orders.dto.ItemParameterRespDto;
import com.qwist.orders.entity.ItemParameter;
import org.mapstruct.Mapper;

/**
 * Takes care of mapping ItemParameter, ItemParameterDto and ItemParameterRespDto
 */
@Mapper
public interface ItemParameterMapper {

    /**
     * Maps ItemParameterDto to ItemParameter
     * @param itemParameterDto Item parameter DTO
     * @return Item Parameter
     */
    ItemParameter itemParameterDtoToItemParameter(ItemParameterDto itemParameterDto);

    /**
     * Maps ItemParameter to ItemParameterRespDto
     * @param itemParameter Item parameter
     * @return Item parameter resp DTO
     */
    ItemParameterRespDto itemToItemParameterRespDto(ItemParameter itemParameter);
}
