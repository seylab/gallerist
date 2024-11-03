package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoAddress;
import com.mehmetolgun.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
