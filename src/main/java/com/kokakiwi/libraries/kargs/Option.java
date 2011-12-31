package com.kokakiwi.libraries.kargs;

public class Option<T>
{
    private String[]       keys        = new String[0];
    
    private boolean        required    = false;
    
    private String         description = "";
    
    private final Class<T> type;
    
    public Option(Class<T> type)
    {
        this.type = type;
    }
    
    public Option<T> required()
    {
        return required(true);
    }
    
    public Option<T> required(boolean required)
    {
        this.required = required;
        
        return this;
    }
    
    public Option<T> desc(String desc)
    {
        this.description = desc;
        
        return this;
    }
    
    public Option<T> keys(String... keys)
    {
        String[] newKeys = new String[this.keys.length + keys.length];
        System.arraycopy(this.keys, 0, newKeys, 0, this.keys.length);
        System.arraycopy(keys, 0, newKeys, this.keys.length, keys.length);
        
        this.keys = newKeys;
        
        return this;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String[] getKeys()
    {
        return keys;
    }
    
    public boolean isRequired()
    {
        return required;
    }
    
    public Class<T> getType()
    {
        return type;
    }
}
