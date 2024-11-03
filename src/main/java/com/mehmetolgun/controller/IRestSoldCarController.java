package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoSoldCar;
import com.mehmetolgun.dto.DtoSoldCarIU;

public interface IRestSoldCarController {

    public RootEntity<DtoSoldCar> buyCar(DtoSoldCarIU dtoSoldCarIU);
}
