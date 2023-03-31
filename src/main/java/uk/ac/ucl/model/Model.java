package uk.ac.ucl.model;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import uk.ac.ucl.datastruct.ItemInterface;
import uk.ac.ucl.datastruct.Item;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;


public class Model
{

  ArrayList<ItemInterface> list = new ArrayList<>();

  public ArrayList<ItemInterface> getItems() throws IOException {
    return this.list;
  }

  public int generateID() throws IOException {
    ArrayList<ItemInterface> itemsList = getItems();
    Random rand = new Random();
    int randomNumber = Math.abs(rand.nextInt());
    for (ItemInterface items : itemsList) {
      if (items.getId() == randomNumber) {
        randomNumber = Math.abs(rand.nextInt());
      }
    }
    return randomNumber;
  }

  public void readFile(File file) throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<ArrayList<ItemInterface>> typeRef = new TypeReference<ArrayList<ItemInterface>>() {};
    this.list = mapper.readValue(file, typeRef);

  }

  // gets a specific item from ID 
  public ItemInterface getSpecificItem(int id) throws IOException {
    ArrayList<ItemInterface> items = getItems();
    for (ItemInterface item : items) {
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

  public void writeJsonArray(ItemInterface item) {
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
      for (ItemInterface item : this.list) {
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

  // gets all ID's that match the laber parameter regardless of case
  public ArrayList<Integer> getItemsIDFromLabel(String label) throws IOException {
    ArrayList<Integer> itemsID = new ArrayList<>();
    for (ItemInterface items : this.list) {
      if (items.getLabel().compareToIgnoreCase(label) == 0) {
        int itemID = items.getId();
        itemsID.add(itemID);
      }
    }
    return itemsID;
  }

  public ItemInterface getItemFromId(int id) throws IOException {
    for (ItemInterface items : this.list) {
      int itemId = items.getId();
      if (itemId == id) {
        return items;
      }
    }
    return null;
  }

  // has three modes: searching everything, by labels, or by values
  public ArrayList<ItemInterface> searchFor(String type, String keyword) throws IOException {
    ArrayList<ItemInterface> fullItemsList = getItems();
    ArrayList<ItemInterface> itemsToReturn = new ArrayList<>();
    for (ItemInterface item : fullItemsList) {
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
