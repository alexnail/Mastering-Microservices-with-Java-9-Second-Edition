package com.packtpub.mmj.user.domain.valueobject;

public record UserVO(String id, String name, String address, String city, String phoneNo) {

    @Override
    public String toString() {
        return "{id: " + id + ", name: "
                + name + ", address: " + address
                + ", city: " + city
                + ", phoneNo: " + phoneNo + "}";
    }
}
