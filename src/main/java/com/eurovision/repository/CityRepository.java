package com.eurovision.repository;


import com.eurovision.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Long>  {

}
