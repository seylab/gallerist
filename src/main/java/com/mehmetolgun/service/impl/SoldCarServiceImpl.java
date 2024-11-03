package com.mehmetolgun.service.impl;

import com.mehmetolgun.dto.*;
import com.mehmetolgun.enums.CarStatusType;
import com.mehmetolgun.exception.BaseException;
import com.mehmetolgun.exception.ErrorMessage;
import com.mehmetolgun.exception.MessageType;
import com.mehmetolgun.model.Car;
import com.mehmetolgun.model.Customer;
import com.mehmetolgun.model.SoldCar;
import com.mehmetolgun.repository.CarRepository;
import com.mehmetolgun.repository.CustomerRepository;
import com.mehmetolgun.repository.GalleristRepository;
import com.mehmetolgun.repository.SoldCarRepository;
import com.mehmetolgun.service.ICurrencyRatesService;
import com.mehmetolgun.service.ISoldCarService;
import com.mehmetolgun.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SoldCarServiceImpl implements ISoldCarService {

    @Autowired
    private SoldCarRepository soldCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    public BigDecimal convertCustomerAmountToUSD(Customer customer) {

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService
                .getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId) {
        Optional<Car> optCar = carRepository.findById(carId);
        if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SOLD.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remaningCustomerAmount(Customer customer , Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        //2000   - 34.15

        return  remaningCustomerUSDAmount.multiply(usd);
    }

    public boolean checkAmount(DtoSoldCarIU dtoSoldCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSoldCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(
                    new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCustomerId().toString()));
        }

        Optional<Car> optCar = carRepository.findById(dtoSoldCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSoldCarIU.getCarId().toString()));
        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

        // 37.000 35.000 = 0 1 -1     =2000
        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
                || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;

    }

    private SoldCar createSoldCar(DtoSoldCarIU dtoSoldCarIU) {
        SoldCar soldCar = new SoldCar();
        soldCar.setCreateTime(new Date());

        soldCar.setCustomer(customerRepository.findById(dtoSoldCarIU.getCustomerId()).orElse(null));
        soldCar.setGallerist(galleristRepository.findById(dtoSoldCarIU.getGalleristId()).orElse(null));
        soldCar.setCar(carRepository.findById(dtoSoldCarIU.getCarId()).orElse(null));

        return soldCar;
    }

    @Override
    public DtoSoldCar buyCar(DtoSoldCarIU dtoSoldCarIU) {

        if(!checkCarStatus(dtoSoldCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SOLD, dtoSoldCarIU.getCarId().toString()));
        }

        if(!checkAmount(dtoSoldCarIU)) {
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }

        SoldCar savedSoldCar = soldCarRepository.save(createSoldCar(dtoSoldCarIU));

        Car car = savedSoldCar.getCar();
        car.setCarStatusType(CarStatusType.SOLD);
        carRepository.save(car);

        Customer customer = savedSoldCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDTO(savedSoldCar);
    }


    public DtoSoldCar toDTO(SoldCar soldCar) {
        DtoSoldCar dtoSoldCar = new DtoSoldCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(soldCar, dtoSoldCar);
        BeanUtils.copyProperties(soldCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(soldCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(soldCar.getCar(), dtoCar);

        dtoSoldCar.setCustomer(dtoCustomer);
        dtoSoldCar.setGallerist(dtoGallerist);
        dtoSoldCar.setCar(dtoCar);
        return dtoSoldCar;
    }

}
