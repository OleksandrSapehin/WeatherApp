package com.example.weatherapp.controllers;

import com.example.weatherapp.dto.MeasurementsDTO;
import com.example.weatherapp.models.Measurement;
import com.example.weatherapp.services.MeasurementService;
import com.example.weatherapp.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.weatherapp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementsDTO measurementsDTO,BindingResult bindingResult){
        Measurement measurementToAdd = convertToMeasurement(measurementsDTO);
        measurementValidator.validate(measurementToAdd,bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);
        measurementService.addMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Measurement> getMeasurements(){
        return measurementService.findAll();
    }
    @GetMapping("/rainyDaysCount")
    public long getRainyDays(){
       return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    private Measurement convertToMeasurement(MeasurementsDTO measurementsDTO){
        return modelMapper.map(measurementsDTO, Measurement.class);
    }

    private MeasurementsDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement,MeasurementsDTO.class);
    }
}
