package com.example.weatherapp.util;

import com.example.weatherapp.models.Sensor;
import com.example.weatherapp.services.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
         Sensor sensor = (Sensor) target;
         if (sensorService.findByName(sensor.getName()).isPresent()){
             errors.rejectValue("name","","This name is already taken");

         }

    }
}
