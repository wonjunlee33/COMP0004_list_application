package uk.ac.ucl.datastruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private String label;
    private String property;
    private int id;
    private HashMap<String, String> otherParameters;

    // defining all parameters for JSON processing
    @JsonCreator
    public Item(@JsonProperty("id") int id, @JsonProperty("label") String label, @JsonProperty("property") String property, @JsonProperty("attributes") HashMap<String,String> otherParameters) {
        this.label = label;
        this.property = property;
        this.id = id;
        this.otherParameters = otherParameters;
    }

    public Item(int id) {
        this(id, null, null, null);
    }
    
    public Item(int id, String label) {
        this(id, label, null, null);
    }

    public Item(int id, String label, String property) {
        this(id, label, property, null);
    }

    // add an item to the map of extra parameters
    public void addItem(String key, String value) {
        otherParameters.put(key, value);
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getProperty() {
        return property;
    }

    // edit label or value parameters
    public void editLabel(String newLabel) {
        label = newLabel;
    }
    
    public void editValue(String newProperty) {
        property = newProperty;
    }

    // get items from hashmap of extra parameters
    public HashMap<String,String> getOtherParameters() {
        return otherParameters;
    }

    public boolean otherParametersContainsKey (String key) {
        return otherParameters.containsKey(key);
    }

    public boolean otherParametersContainsValue (String value) {
        return otherParameters.containsValue(value);
    }

    // remove an item from the map
    public void removeOtherParameter(String key) {
        otherParameters.remove(key);
    }

    public ArrayList<String> generateKeyWordsWithoutLabels() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add(String.valueOf(id));
        keywords.add(property);
        for (Map.Entry<String,String> entry : otherParameters.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            keywords.add(k);
            keywords.add(v);
        }
        return keywords;
    }

    // generate keywords for full search
    public ArrayList<String> generateKeyWords() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add(label);
        keywords.add(String.valueOf(id));
        keywords.add(property);
        for (Map.Entry<String,String> entry : otherParameters.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            keywords.add(k);
            keywords.add(v);
        }
        return keywords;
    }
}

