package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoAccount;
import com.mehmetolgun.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
