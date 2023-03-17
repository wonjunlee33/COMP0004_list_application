package uk.ac.ucl.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;


public class Model
{
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.

  public ArrayList<HashMap<String, String>> getItems() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("src/main/java/uk/ac/ucl/storage/items.json");
    TypeReference<ArrayList<HashMap<String, String>>> typeRef = new TypeReference<ArrayList<HashMap<String, String>>>() {};
    ArrayList<HashMap<String, String>> items = mapper.readValue(file, typeRef);
    return items;
  }

  public void readFile(File file)
  {

  }

  public HashMap<String, String> formatInputForAdd(String input) {
    HashMap<String, String> result = new HashMap<>();

    String[] keyValuePairs = input.split("/");
    if (keyValuePairs.length % 2 != 0) {
        throw new IllegalArgumentException("Input string has an odd number of elements.");
    }

    for (int i = 0; i < keyValuePairs.length; i += 2) {
        String key = keyValuePairs[i];
        String value = keyValuePairs[i+1];
        result.put(key, value);
    }

    return result;
}

  public void writeToJsonFile(HashMap<String, String> newItem) {
    try {
        File file = new File("src/main/java/uk/ac/ucl/storage/items.json");

        // Load existing JSON data into a list of maps
        List<HashMap<String, String>> existingData = readJsonArray(file);

        // Add new data to the list
        existingData.add(newItem);

        // Write the updated list to the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, existingData);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public List<HashMap<String, String>> readJsonArray(File file) {
    List<HashMap<String, String>> result = new ArrayList<>();
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {};
                HashMap<String, String> map = objectMapper.convertValue(node, typeRef);
                result.add(map);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return result;
}

public void deleteItem(int id) {
  try {
    File file = new File("src/main/java/uk/ac/ucl/storage/items.json");

    // Load existing JSON data into a list of maps
    List<HashMap<String, String>> existingData = readJsonArray(file);

    // Add new data to the list
    existingData.remove(id);

    // Write the updated list to the JSON file
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(file, existingData);
} catch (IOException e) {
    e.printStackTrace();
}
}


  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public List<String> searchFor(String keyword)
  {
    return List.of("Search keyword is: "+ keyword, "result1", "result2", "result3");
  }
}
