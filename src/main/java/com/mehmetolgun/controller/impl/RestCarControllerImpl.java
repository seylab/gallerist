package com.mehmetolgun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mehmetolgun.controller.IRestCarController;
import com.mehmetolgun.controller.RestBaseController;
import com.mehmetolgun.controller.RootEntity;
import com.mehmetolgun.dto.DtoCar;
import com.mehmetolgun.dto.DtoCarIU;
import com.mehmetolgun.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }

}
