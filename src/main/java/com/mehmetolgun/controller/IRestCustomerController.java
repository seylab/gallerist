package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoCustomer;
import com.mehmetolgun.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}