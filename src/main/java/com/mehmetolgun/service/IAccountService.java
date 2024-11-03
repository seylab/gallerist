package com.mehmetolgun.service;


import com.mehmetolgun.dto.DtoAccount;
import com.mehmetolgun.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
