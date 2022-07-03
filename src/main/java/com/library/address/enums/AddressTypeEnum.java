package com.library.address.enums;

public enum AddressTypeEnum {
    HOME_ADDRESS(1),
    WORK_ADDRESS(2),
    OTHER(3);

    private int addressValue;

    AddressTypeEnum(int addressValue) {
        this.addressValue = addressValue;
    }

    public int getAddressValue(){
        return this.addressValue;
    }
}
