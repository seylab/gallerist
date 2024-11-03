package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoCar;
import com.mehmetolgun.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
