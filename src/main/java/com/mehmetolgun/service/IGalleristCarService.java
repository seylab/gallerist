package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoGalleristCar;
import com.mehmetolgun.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
