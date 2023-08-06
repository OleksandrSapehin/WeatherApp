package com.example.weatherapp.services;

import com.example.weatherapp.models.Sensor;
import com.example.weatherapp.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService  {
    private final  SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public Sensor saveSensor(Sensor sensor){
        return  sensorRepository.save(sensor);
    }
    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }



}
