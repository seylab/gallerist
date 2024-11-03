package com.mehmetolgun.controller;

import com.mehmetolgun.dto.DtoGallerist;
import com.mehmetolgun.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
