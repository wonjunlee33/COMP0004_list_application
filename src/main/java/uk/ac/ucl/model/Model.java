package uk.ac.ucl.model;

import java.io.File;
import java.util.*;

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

  public int generateID() throws IOException {
    ArrayList<HashMap<String,String>> itemsList = getItems();
    Random rand = new Random();
    int randomNumber = Math.abs(rand.nextInt());
    for (HashMap<String,String> items : itemsList) {
      if (items.get("id").compareTo(String.valueOf(randomNumber)) == 0) {
        randomNumber = Math.abs(rand.nextInt());
      }
    }
    return randomNumber;
  }

  public void readFile(File file)
  {

  }

  public HashMap<String,String> getSpecificItem(int id) throws IOException {
    ArrayList<HashMap<String,String>> items = getItems();
    for (HashMap<String,String> item : items) {
      if (item.get("id").compareTo(String.valueOf(id)) == 0) {
        return item;
      }
    }
    return null;
  }

  public HashMap<String, String> formatInput(String input) {
    HashMap<String, String> result = new HashMap<>();

    String[] keyValuePairs = input.split("\\|");
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

  public String deFormatInput(HashMap<String,String> item) {
    StringBuilder sb = new StringBuilder();
    for (HashMap.Entry<String, String> entry : item.entrySet()) {
        sb.append(entry.getKey()).append("|").append(entry.getValue()).append("|");
    }
    
    // get rid of last slash
    String result = sb.toString();
    if (result.endsWith("|")) {
        result = result.substring(0, result.length() - 1);
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

      // remove the old data
      HashMap<String,String> toDelete = new HashMap<>();
      for (HashMap<String,String> item : existingData) {
        if (item.get("id").compareTo(String.valueOf(id)) == 0) {
          toDelete = item;
        }
      }
      existingData.remove(toDelete);

      // Write the updated list to the JSON file
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(file, existingData);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public ArrayList<Integer> getItemsIDFromLabel(String label) throws IOException {
    ArrayList<HashMap<String,String>> fullListOfItems = getItems();
    ArrayList<Integer> itemsID = new ArrayList<>();
    for (HashMap<String,String> items : fullListOfItems) {
      if (items.get("label").compareToIgnoreCase(label) == 0) {
        int itemID = Integer.parseInt(items.get("id"));
        itemsID.add(itemID);
      }
    }
    return itemsID;
  }

  public HashMap<String,String> getHashMapFromId(int id) throws IOException {
    ArrayList<HashMap<String,String>> fullItemsList = getItems();
    for (HashMap<String,String> items : fullItemsList) {
      int itemId = Integer.parseInt(items.get("id"));
      if (itemId == id) {
        return items;
      }
    }
    return null;
  }


  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public List<String> searchFor(String keyword)
  {
    return List.of("Search keyword is: "+ keyword, "result1", "result2", "result3");
  }
}
