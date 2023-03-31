package uk.ac.ucl.datastruct;
import java.util.ArrayList;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Item.class, name = "item")
})

public interface ItemInterface {
    
    public void addItem(String key, String value);

    public int getId();

    public String getLabel();

    public String getProperty();

    public void editLabel(String newLabel);

    public void editValue(String newProperty);

    public HashMap<String,String> getOtherParameters();

    public boolean otherParametersContainsKey (String key);

    public boolean otherParametersContainsValue (String value);

    public void removeOtherParameter(String key);

    public ArrayList<String> generateKeyWordsWithoutLabels();

    public ArrayList<String> generateKeyWords();

}
