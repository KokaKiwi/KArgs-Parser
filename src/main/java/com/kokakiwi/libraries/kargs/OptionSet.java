package com.kokakiwi.libraries.kargs;

import java.util.LinkedHashMap;
import java.util.Map;

public class OptionSet
{
    private final Map<Option<?>, Object> values = new LinkedHashMap<Option<?>, Object>();
    private final Map<String, Option<?>> keys   = new LinkedHashMap<String, Option<?>>();
    
    public <T> void putOptionValue(Option<T> option, T value)
    {
        values.put(option, value);
        for (String key : option.getKeys())
        {
            keys.put(key, option);
        }
    }
    
    public Object getOptionValue(String key, Object def)
    {
        return getOptionValue(Object.class, key, def);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getOptionValue(Class<T> clazz, String key, T def)
    {
        return (T) getOptionValue((Option<T>) keys.get(key), def);
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getOptionValue(Option<T> option, T def)
    {
        T value = (T) values.get(option);
        
        if (value == null)
        {
            value = def;
        }
        
        return value;
    }
    
    public boolean hasOption(String key)
    {
        return hasOption(keys.get(key));
    }
    
    @SuppressWarnings("unchecked")
    public <T> boolean hasOption(Class<T> clazz, String key)
    {
        return hasOption((Option<T>) keys.get(key));
    }
    
    public <T> boolean hasOption(Option<T> option)
    {
        return getOptionValue(option, null) != null;
    }
}
