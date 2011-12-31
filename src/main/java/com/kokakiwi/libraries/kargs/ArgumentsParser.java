package com.kokakiwi.libraries.kargs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsParser
{
    private final List<Option<?>> acceptedOptions = new ArrayList<Option<?>>();
    
    public <T> Option<T> accept(Class<T> type)
    {
        Option<T> option = new Option<T>(type);
        
        acceptedOptions.add(option);
        
        return option;
    }
    
    public OptionSet parse(String... args) throws NoSuchMethodException,
            SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        OptionSet set = new OptionSet();
        
        // Temp values
        Option<Object> currentOption = null;
        
        // Loop
        for (String arg : args)
        {
            if (currentOption != null)
            {
                putValue(set, currentOption, getValue(arg, currentOption));
                currentOption = null;
            }
            else
            {
                int index = arg.lastIndexOf('-');
                
                if (index >= 0)
                {
                    String key = arg.substring(index + 1);
                    currentOption = getOption(key);
                }
            }
        }
        
        return set;
    }
    
    @SuppressWarnings("unchecked")
    public <T> Option<T> getOption(String key)
    {
        Option<T> option = null;
        
        for (Option<?> opt : acceptedOptions)
        {
            for (String k : opt.getKeys())
            {
                if (key.equalsIgnoreCase(k))
                {
                    option = (Option<T>) opt;
                    break;
                }
            }
            
            if (option != null)
            {
                break;
            }
        }
        
        return option;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getValue(String arg, Option<T> option)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException
    {
        Class<T> type = option.getType();
        T value = null;
        
        if (type == String.class)
        {
            value = (T) arg;
        }
        else
        {
            Method valueOfMethod = type.getDeclaredMethod("valueOf",
                    String.class);
            if (valueOfMethod.getReturnType() == type)
            {
                value = (T) valueOfMethod.invoke(null, arg);
            }
        }
        
        return value;
    }
    
    public <T> void putValue(OptionSet set, Option<T> option, T value)
    {
        set.putOptionValue(option, value);
    }
}
