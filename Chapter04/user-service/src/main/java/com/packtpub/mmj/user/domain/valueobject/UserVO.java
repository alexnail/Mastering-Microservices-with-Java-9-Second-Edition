package com.packtpub.mmj.user.domain.valueobject;

public record UserVO(String id, String name, String address, String city, String phoneNo) {

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, address: %s, city: %s, phoneNo: %s}",
                id, name, address, city, phoneNo);
    }
}
