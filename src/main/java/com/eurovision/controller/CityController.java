package com.eurovision.controller;

import java.util.List;

import com.eurovision.algorithm.Algorithm;
import com.eurovision.exception.RecordNotFoundException;
import com.eurovision.model.City;
import com.eurovision.response.CustomHttpResponse;
import com.eurovision.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@CrossOrigin()
public class CityController {

    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<List<City>> getAllStudents() {
        List<City> list = service.getAllCities();

        return new ResponseEntity<List<City>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        City entity = service.getCityById(id);

        return new ResponseEntity<City>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/queryByPage")
    public ResponseEntity<CustomHttpResponse> getCitiesByPageAndSize(
            @RequestParam(name = "page" ,defaultValue = "0") Integer page,
            @RequestParam(name = "size" ,defaultValue = "10") Integer size)
    {
        List<City> list = service.getCitiesByPageAndSize(page, size);
        Long count = service.countCities();
        double totalPages = Math.ceil(count.floatValue()/size.floatValue());

        CustomHttpResponse response = new CustomHttpResponse(list, (int) totalPages,count,page>=totalPages,size,page);

        return new ResponseEntity<CustomHttpResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/runAlgorithm")
    public ResponseEntity<List<City>> getCitiesByAlgorithm()
    {
        Long count = service.countCities();
        List<City> list = service.getCitiesByPageSizeAndSortedByName(0, count.intValue());
        Algorithm alg = new Algorithm(list);
        List<City> result = alg.runAlgorithm();
        return new ResponseEntity<List<City>>(result, new HttpHeaders(), HttpStatus.OK);
    }

}
