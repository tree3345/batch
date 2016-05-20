package com.lzjf.util;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
  
public class ObjectMappingCustomer extends ObjectMapper  
{  
  
    public ObjectMappingCustomer()  
    {  
        super();  
        this.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        this.setVisibility(JsonMethod.FIELD	, Visibility.ANY); 
        this.configure(Feature.USE_ANNOTATIONS,false);
        this.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }  
}  