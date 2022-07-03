package com.library.address.model;

import com.library.address.enums.AddressTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

    private String addressExplanation;

    private AddressTypeEnum addressType;

    private Boolean active;
}
