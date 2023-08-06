package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.SensorDTO;
import com.example.weatherapp.models.Sensor;
import com.example.weatherapp.services.SensorService;
import com.example.weatherapp.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.weatherapp.util.ErrorsUtil.returnErrorsToClient;

@RestController
public class SensorsController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;


    public SensorsController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

   @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorToAdd = convertToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd,bindingResult);
       if (bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
       }
       sensorService.saveSensor(sensorToAdd);
       return ResponseEntity.ok(HttpStatus.CREATED);
   }
   @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);

   }
   private Sensor convertToSensor(SensorDTO sensorDTO){
        return  modelMapper.map(sensorDTO, Sensor.class);
   }
   private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor,SensorDTO.class);
   }







}
