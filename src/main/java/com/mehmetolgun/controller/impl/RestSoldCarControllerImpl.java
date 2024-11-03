package com.mehmetolgun.controller.impl;

import com.mehmetolgun.controller.IRestSoldCarController;
import com.mehmetolgun.controller.RestBaseController;
import com.mehmetolgun.controller.RootEntity;
import com.mehmetolgun.dto.DtoSoldCar;
import com.mehmetolgun.dto.DtoSoldCarIU;
import com.mehmetolgun.service.ISoldCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSoldCarControllerImpl extends RestBaseController implements IRestSoldCarController {

    @Autowired
    private ISoldCarService soldCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSoldCar> buyCar(@Valid @RequestBody DtoSoldCarIU dtoSoldCarIU) {
        return ok(soldCarService.buyCar(dtoSoldCarIU));
    }
}
