package com.mehmetolgun.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase{

    private String city;

    private String district;

    private String neighborhood;

    private String street;
}
