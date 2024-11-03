package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoAddress;
import com.mehmetolgun.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
