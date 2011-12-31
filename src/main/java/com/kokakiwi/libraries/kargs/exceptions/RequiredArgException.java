package com.kokakiwi.libraries.kargs.exceptions;

import java.util.Arrays;

import com.kokakiwi.libraries.kargs.Option;

public class RequiredArgException extends Exception
{
    private static final long serialVersionUID = -1404842814135442426L;
    
    public RequiredArgException(Option<?> option)
    {
        super("Required arg not mentioned: "
                + Arrays.toString(option.getKeys()));
    }
}
