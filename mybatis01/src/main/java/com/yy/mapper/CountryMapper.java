package com.yy.mapper;

import com.yy.model.Country;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CountryMapper {
    Country getCountryByID(@Param("id") Long id);
    List<Country> getCountrys();

    List<Country> getCountrysByCountryName(String CountryName);

    int deleteByID(Long id);
}
