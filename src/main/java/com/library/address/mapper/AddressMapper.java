package com.library.address.mapper;

import com.library.address.entity.Address;
import com.library.address.model.AddressRequestDto;
import com.library.address.model.AddressResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    List<Address> convertFromAddressRequestDtosToAddresses(List<AddressRequestDto> addressRequestDto);

    List<AddressResponseDto> convertFromAddressesToAddressResponseDtos(List<Address> address);

    List<AddressRequestDto> convertFromAddressesToAddressRequestDtos(List<Address> address);

}
