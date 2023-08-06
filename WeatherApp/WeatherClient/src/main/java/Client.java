import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public Client() {
    }

    public static void main(String[] args) {
        String sensorName = "Sensor123";
        registerSensor("Sensor123");
        Random random = new Random();
        double minTemperature = 0.0;
        double maxTemperature = 45.0;

        for(int i = 0; i < 500; ++i) {
            System.out.println(i);
            sendMeasurement(random.nextDouble() * maxTemperature, random.nextBoolean(), "Sensor123");
        }

    }

    private static void registerSensor(String sensorName) {
        String url = "http://localhost:8080/registration";
        Map<String, Object> jsonData = new HashMap();
        jsonData.put("name", sensorName);
        makePostRequestWithJSONData("http://localhost:8080/registration", jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName) {
        String url = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));
        makePostRequestWithJSONData("http://localhost:8080/measurements/add", jsonData);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class, new Object[0]);
            System.out.println("Successfully sent!");
        } catch (HttpClientErrorException var6) {
            System.out.println("Error!");
            System.out.println(var6.getMessage());
        }

    }
}
