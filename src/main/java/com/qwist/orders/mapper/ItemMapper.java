package com.qwist.orders.mapper;

import com.qwist.orders.dto.ItemDto;
import com.qwist.orders.dto.ItemRespDto;
import com.qwist.orders.entity.Item;
import org.mapstruct.Mapper;

/**
 * Takes care of mapping Item, ItemDto and ItemRespDto
 */
@Mapper(uses = ItemParameterMapper.class)
public interface ItemMapper {

    /**
     * Maps ItemDto to Item
     * @param itemDto Item DTO
     * @return Item
     */
    Item itemDtoToItem(ItemDto itemDto);

    /**
     * Maps Item to ItemRespDto
     * @param item Item
     * @return Item resp DTO
     */
    ItemRespDto itemToItemRespDto(Item item);

}
