package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoGalleristCar;
import com.mehmetolgun.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
