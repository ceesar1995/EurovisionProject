package com.eurovision.service;


import com.eurovision.exception.RecordNotFoundException;
import com.eurovision.model.City;
import com.eurovision.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    public List<City> getAllCities()
    {
        List<City> cityList = repository.findAll();

        if(cityList.size() > 0) {
            return cityList;
        } else {
            return new ArrayList<City>();
        }
    }

    public City getCityById(Long id) throws RecordNotFoundException
    {
        Optional<City> city = repository.findById(id);

        if(city.isPresent()) {
            return city.get();
        } else {
            throw new RecordNotFoundException("No city record exist for given id",id);
        }
    }

    public List<City> getCitiesByPageAndSize(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<City> pagedResult = repository.findAll(paging);


        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<City>();
        }
    }

    public List<City> getCitiesByPageSizeAndSortedByName(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by("name").ascending());

        Page<City> pagedResult = repository.findAll(paging);


        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<City>();
        }
    }

    public Long countCities()
    {

        Long count = repository.count();

        return count;

    }


}
