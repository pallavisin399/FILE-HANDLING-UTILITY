import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class WeatherApp {
    public static void main(String[] args) throws IOException {
        // Your API key from OpenWeatherMap
        String apiKey = "YOUR_API_KEY";  
        // City name (change as needed)
        String city = "London";  
        
        // URL for the OpenWeatherMap API
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        
        // Create a URL object and establish a connection
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse JSON response
        JSONObject jsonResponse = new JSONObject(response.toString());
        
        // Extract weather data from the response
        String cityName = jsonResponse.getString("name");
        double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
        int humidity = jsonResponse.getJSONObject("main").getInt("humidity");
        String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

        // Display weather data
        System.out.println("Weather Information for " + cityName + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
    }
}
