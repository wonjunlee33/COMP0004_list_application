package uk.ac.ucl.model;

import java.io.File;
import java.util.*;
import uk.ac.ucl.datastruct.Item;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;


public class Model
{
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  ArrayList<Item> list = new ArrayList<>();

  public ArrayList<Item> getItems() throws IOException {
    return this.list;
  }

  public int generateID() throws IOException {
    ArrayList<Item> itemsList = getItems();
    Random rand = new Random();
    int randomNumber = Math.abs(rand.nextInt());
    for (Item items : itemsList) {
      if (items.getId() == randomNumber) {
        randomNumber = Math.abs(rand.nextInt());
      }
    }
    return randomNumber;
  }

  public void readFile(File file) throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<ArrayList<Item>> typeRef = new TypeReference<ArrayList<Item>>() {};
    this.list = mapper.readValue(file, typeRef);

  }

  public Item getSpecificItem(int id) throws IOException {
    ArrayList<Item> items = getItems();
    for (Item item : items) {
      if (item.getId() == id) {
        return item;
      }
    }
    return null;
  }

  public List<Item> readJsonArray(File file) {
    List<Item> result = new ArrayList<>();
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);
        if (rootNode.isArray()) {
            for (JsonNode node : rootNode) {
                Item item = objectMapper.treeToValue(node, Item.class);
                System.out.println(Item.class);
                result.add(item);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return result;
  }

  public void writeJsonArray(Item item) {
      try {
        File file = new File("src/main/java/uk/ac/ucl/storage/items.json");

        // Load existing JSON data into a list of maps

        this.list.add(item);
        // Write the updated list to the JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, this.list);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public void deleteItem(int id) {
    try {
      File file = new File("src/main/java/uk/ac/ucl/storage/items.json");

      // remove the old data
      for (Item item : this.list) {
        if (item.getId() == id) {
          this.list.remove(item);
          break;
        }
      }

      // Write the updated list to the JSON file
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(file, this.list);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }

  public ArrayList<Integer> getItemsIDFromLabel(String label) throws IOException {
    ArrayList<Integer> itemsID = new ArrayList<>();
    for (Item items : this.list) {
      if (items.getLabel().compareToIgnoreCase(label) == 0) {
        int itemID = items.getId();
        itemsID.add(itemID);
      }
    }
    return itemsID;
  }

  public Item getItemFromId(int id) throws IOException {
    for (Item items : this.list) {
      int itemId = items.getId();
      if (itemId == id) {
        return items;
      }
    }
    return null;
  }


  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public ArrayList<Item> searchFor(String type, String keyword) throws IOException {
    ArrayList<Item> fullItemsList = getItems();
    ArrayList<Item> itemsToReturn = new ArrayList<>();
    for (Item item : fullItemsList) {
        boolean found = false;
        ArrayList<String> keywords = item.generateKeyWords();
        ArrayList<String> valueKeywords = item.generateKeyWordsWithoutLabels();
        if (type.compareTo("everything") == 0) {
          if (keywords.contains(keyword)) {
            found = true;
          }
        } else if (type.compareTo("label") == 0) {
            if (item.getLabel().compareToIgnoreCase(keyword) == 0) {
              found = true;
            }
        } else if (type.compareTo("values") == 0) {
            if (valueKeywords.contains(keyword)) {
              found = true;
            }
        }

        if (found) {
            itemsToReturn.add(item);
        }
      }
      return itemsToReturn;
    }

}
