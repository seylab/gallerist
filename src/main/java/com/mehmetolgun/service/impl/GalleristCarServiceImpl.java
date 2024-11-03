package com.mehmetolgun.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehmetolgun.dto.DtoAddress;
import com.mehmetolgun.dto.DtoCar;
import com.mehmetolgun.dto.DtoGallerist;
import com.mehmetolgun.dto.DtoGalleristCar;
import com.mehmetolgun.dto.DtoGalleristCarIU;
import com.mehmetolgun.exception.BaseException;
import com.mehmetolgun.exception.ErrorMessage;
import com.mehmetolgun.exception.MessageType;
import com.mehmetolgun.model.Car;
import com.mehmetolgun.model.Gallerist;
import com.mehmetolgun.model.GalleristCar;
import com.mehmetolgun.repository.CarRepository;
import com.mehmetolgun.repository.GalleristCarRepository;
import com.mehmetolgun.repository.GalleristRepository;
import com.mehmetolgun.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService{

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if(optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if(optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }


        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;

    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        DtoAddress dtoAddress = new DtoAddress();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);

        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);


        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }

}