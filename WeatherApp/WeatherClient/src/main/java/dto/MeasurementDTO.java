package dto;

public class MeasurementDTO {
    private Double value;
    private boolean raining;
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean getRaining() {
        return this.raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return this.sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
