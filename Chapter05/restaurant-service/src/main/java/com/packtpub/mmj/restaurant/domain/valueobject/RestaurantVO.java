package com.packtpub.mmj.restaurant.domain.valueobject;

import com.packtpub.mmj.restaurant.domain.model.entity.Table;
import java.util.List;

/**
 * @author Sourabh Sharma
 */
public record RestaurantVO(String id, String name, String address, List<Table> tables) {

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, tables: %s}", id, name, address, tables);
    }
}
