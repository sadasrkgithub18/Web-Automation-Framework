package com.nagarro.supporters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader 
{
  private String filePath;
  private FileInputStream fip;
  private Properties properties;
  
  public PropertiesReader(String filePath) throws IOException
  {
	  this.filePath = filePath;
	  fip = new FileInputStream(filePath);
	  properties = new Properties();
	  Optional<Properties> optional = Optional.ofNullable(properties);
	  
	  if(optional.isPresent())
	  {
		  properties.load(fip);
	  }
	  else
	  {
		  System.out.println("Properties Object is pointing to Null");
	  }
	  
  }
	
   
     public String getPropertyValue(String key) 
     { 	 
    	String value =  properties.getProperty(key);
     	return value;   	 
     }
     
     
     public String getPropertyValue(String key, String defaultValue)
     {
    	 
    	String value =  properties.getProperty(key);
    	 
    	Optional<String> optional = Optional.ofNullable(value);
    	if(optional.isPresent())
    	{
    		// Don't do any thing
    	}
    	 
    	else
    	{
    		value = defaultValue;
    	}
		return value;
    	 
     }
  
  
	 public String getProperty(Object key)
	 {
		String value =  (String) properties.get(key);
		return value;		 
	 }
	 
	 public String getProperty(Object key, Object defaultValue)
	 {
		 
		String value =  (String) properties.getOrDefault(key, defaultValue);
		Optional<String> optional = Optional.ofNullable(value);
		if(optional.isPresent())
		{
			//Don't do any thing
		}
		
		else
		{
			value = (String) defaultValue;
		}
		
		return value;
		 
	 }
	
	 
	 public void writeData(String key, String value) throws FileNotFoundException, IOException
	 {
		 properties.setProperty(key, value);
		 properties.store(new FileOutputStream(filePath), "Saved Successfully");
		 
	 }
	 
	 
	 public void writeData(Object key, Object value) throws FileNotFoundException, IOException
	 {
		 properties.put(key, value);
		 properties.store(new FileOutputStream(filePath), "Saved Successfully");
	 }
	 
	 
	 public Set<Object> getOnlyKeys()
	 {	
		
		Set<Object> keys = properties.keySet();
    	return keys;	 
	 }
	 
	 
	 public List<String> getOnlyValues()
	 {
	    List<String> values = new ArrayList<String>();	 
	    Set<Object> keys = getOnlyKeys();
		for(Object obj : keys )
		{
			String key = (String) obj;
			String value = properties.getProperty(key);
			System.out.println(key+"="+value);
			
			values.add(value);
		}    
		return values;	 
	 }
	 
    public static void main(String[] args) throws IOException 
    {
    	PropertiesReader prop =new PropertiesReader("D:\\OOPSAndPOMDemo\\config.properties");
       
		/*
		 * List<String> values = prop.getOnlyValues(); Set<Object> keys =
		 * prop.getOnlyKeys();
		 */
        String vall = prop.getPropertyValue("gmail_url");
        System.out.println(vall);
        
        System.out.println("===========================");
        
		/*
		 * Object a = "gmail_un_id"; System.out.println(prop.getProperty(a));
		 * 
		 * System.out.println("===========================");
		 * 
		 * Object b = "gmail_next_id123"; Object c = "Object c default value";
		 * 
		 * System.out.println(prop.getProperty(b,c));
		 * 
		 * System.out.println("===========================");
		 * 
		 * 
		 * String idd = prop.getProperty("gmail_next_id"); System.out.println(idd);
		 * 
		 * String mn = prop.getPropertyValue("gmail_next","Default Value");
		 * System.out.println(mn);
		 * 
		 * String ll = prop.getProperty("gmail_next", "Object default value");
		 * System.out.println(ll);
		 * 
		 * 
		 * System.out.println(keys); System.out.println(values);
		 */        
	}
}
