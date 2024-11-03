package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoCustomer;
import com.mehmetolgun.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}