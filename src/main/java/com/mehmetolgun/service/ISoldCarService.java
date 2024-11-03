package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoSoldCar;
import com.mehmetolgun.dto.DtoSoldCarIU;

public interface ISoldCarService {

    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU);
}
