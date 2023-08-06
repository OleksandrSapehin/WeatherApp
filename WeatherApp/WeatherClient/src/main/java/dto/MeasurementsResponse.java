package dto;

import java.util.List;

public class MeasurementsResponse {
    List<MeasurementDTO> measurements;

    public MeasurementsResponse() {
    }

    public List<MeasurementDTO> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
