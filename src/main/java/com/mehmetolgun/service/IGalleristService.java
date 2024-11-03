package com.mehmetolgun.service;

import com.mehmetolgun.dto.DtoGallerist;
import com.mehmetolgun.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}