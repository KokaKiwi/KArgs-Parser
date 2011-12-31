package com.kokakiwi.libraries.kargs.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.kokakiwi.libraries.kargs.ArgumentsParser;
import com.kokakiwi.libraries.kargs.Option;
import com.kokakiwi.libraries.kargs.OptionSet;

public class ExampleTest
{
    public final static String TEST_VALUE = "test";
    
    @Test
    public void example()
    {
        ArgumentsParser parser = new ArgumentsParser();
        
        Option<String> strOption = parser.accept(String.class).keys("str", "s");
        Option<Integer> intOption = parser.accept(Integer.class).keys("int")
                .required();
        Option<File> fileOption = parser.accept(File.class).keys("file");
        
        OptionSet set = null;
        try
        {
            set = parser.parse("-str", TEST_VALUE, "--file", "../hello_world",
                    "-------int", "25");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if (set != null)
        {
            if (set.hasOption(strOption))
            {
                String value = set.getOptionValue(strOption, null);
                assertEquals(value, TEST_VALUE);
            }
        }
    }
    
}
