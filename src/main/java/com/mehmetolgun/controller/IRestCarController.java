package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoCar;
import com.mehmetolgun.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
