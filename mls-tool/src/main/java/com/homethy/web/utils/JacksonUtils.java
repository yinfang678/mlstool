package com.homethy.web.utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class JacksonUtils {

    private final static ObjectMapper objMapper = new ObjectMapper();
    
    static{
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    /**
     * 调用get方法生成json字符串 <br>
     * 2015年1月27日:下午12:26:55<br>
     * <br>
     * 
     * @param obj
     * @return
     * @throws IOException
     */
    public static String toJson(Object obj) {
        try {
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对obj对象进行序列话，序列化是依据jsonViewClazz的配置
     * 
     * @param obj
     * @param jsonViewClazz
     * @return
     */
    public static <T> String toJson(Object obj, Class<T> jsonViewClazz) {
        try {
            return objMapper.writerWithView(jsonViewClazz).writeValueAsString(
                obj);
        } catch (Exception e) {
        	return null;
        }
    }

    /**
     * 利用Jackson序列化时，指定clazz类型及其需要包含的输出属性
     * 
     * @param obj 需要序列化的对象
     * @param clazz 指定的类型
     * @param includeFileds 指定的需要输出的属性
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String toJsonWithInclude(Object obj, Class clazz,
        String... includeFileds) {
        Map<Class, Set<String>> include = new HashMap<Class, Set<String>>();
        include.put(clazz, new HashSet<String>(Arrays.asList(includeFileds)));
        return toJson(obj, include, null);
    }

    /**
     * 利用Jackson序列化时，指定多个类型及其需要输出的属性
     * 
     * @param obj 需要序列化的对象
     * @param include 每种类型对应的需要输出的属性
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String toJsonWithInclude(Object obj,
        Map<Class, Set<String>> include) {
        return toJson(obj, include, null);
    }

    /**
     * 利用Jackson序列化时，指定clazz代表的类型需要过滤掉得属性
     * 
     * @param obj 需要序列化的对象
     * @param clazz 指定的类型
     * @param excludeFields 需要排除掉得字段
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String toJsonWithExclude(Object obj, Class clazz,
        String... excludeFields) {
        Map<Class, Set<String>> exclude = new HashMap<Class, Set<String>>();
        exclude.put(clazz, new HashSet<String>(Arrays.asList(excludeFields)));
        return toJson(obj, null, exclude);
    }

    /**
     * 利用Jackson序列化时，指定需要多个类型及其需要过滤掉得属性
     * 
     * @param obj 序列化的对象
     * @param exclude 每种类型对应的需要排除的属性
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String toJsonWithExclude(Object obj,
        Map<Class, Set<String>> exclude) {
        return toJson(obj, null, exclude);
    }

    /**
     * 利用Jackson序列化时，指定各种类型及其对应的过滤条件<br>
     * <br>
     * include exclude可以其中之一为空或者同时为空<br>
     * include为空只过滤exclude<br>
     * exclude为空，只根据include的配置输出字段<br>
     * 同时为空时不进行过滤
     * 
     * @param obj 需要序列化的对象
     * @param include 指定class序列化时需要包含的属性
     * @param exclude 指定class序列化时需要排除的属性
     * @return 根据include exclude进行属性的过滤后的对象生成的json 串
     */
    @SuppressWarnings({ "serial", "rawtypes" })
    public static String toJson(Object obj, Map<Class, Set<String>> include,
        Map<Class, Set<String>> exclude) {

        if ((null == include || include.isEmpty())
            && (null == exclude || exclude.isEmpty())) {
            toJson(obj);
        }

        ObjectMapper mapper = new ObjectMapper();

        // 设置包含过滤器
        FilterProvider filters = new SimpleFilterProvider();
        if (null != include && !include.isEmpty()) {
            for (Entry<Class, Set<String>> entry : include.entrySet()) {
                Class clazz = entry.getKey();
                Set<String> includeFileds = entry.getValue();
                ((SimpleFilterProvider) filters).addFilter(clazz.getName(),
                    SimpleBeanPropertyFilter.filterOutAllExcept(includeFileds));
            }
        }

        // 设置排除过滤器
        if (null != exclude && !exclude.isEmpty()) {
            for (Entry<Class, Set<String>> entry : exclude.entrySet()) {
                Class clazz = entry.getKey();
                Set<String> excludeFileds = entry.getValue();
                ((SimpleFilterProvider) filters).addFilter(clazz.getName(),
                    SimpleBeanPropertyFilter.serializeAllExcept(excludeFileds));
            }
        }
        mapper.setFilterProvider(filters);

        // 都是有哪些过滤器名
        final Set<String> filterNames = new HashSet<String>();
        if (null != include && !include.isEmpty()) {
            for (Class clazz : include.keySet()) {
                filterNames.add(clazz.getName());
            }
        }
        if (null != exclude && !exclude.isEmpty()) {
            for (Class clazz : exclude.keySet()) {
                filterNames.add(clazz.getName());
            }
        }

        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector() {

            public Object findFilterId(Annotated ac) {
                String name = ac.getName();
                if (filterNames.contains(name)) {
                    return name;
                } else {
                    return null;
                }
            }
        });

        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
        	return null;
        }
    }

    /**
     * 转换成jsonnode
     * 
     * @param jsonText
     * @return
     */
    public static JsonNode toJsonNode(String jsonText) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objMapper.readTree(jsonText);
        } catch (Exception e) {
        	return null;
        }
        return jsonNode;
    }

    /**
     * 转换json为clazz. <br>
     * <strong>依赖get和set方法</strong> <br>
     * 2015年1月27日:下午12:26:18<br>
     * <br>
     * 
     * @param jsonText
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T fromJson(String jsonText, Class<T> clazz)
        throws IOException {
        if (jsonText == null || "".equals(jsonText)) {
            return null;
        }

        return objMapper.readValue(jsonText, clazz);
    }

    /**
     * 转换为集合类型的对象集合 <strong>依赖get和set方法</strong> <br>
     * 2015年3月10日:上午11:19:14<br>
     * <br>
     * <strong>example:</strong>
     * 
     * <pre>
     * JacksonUtils.fromJson(jsonText, new TypeReference&lt;List&lt;FeedImage&gt;&gt;() {
     * });
     * </pre>
     * 
     * @param jsonText
     * @param valueTypeRef org.codehaus.jackson.type.TypeReference
     * @return
     * @throws Exception
     */
    public static <T> T fromJson(String jsonText, TypeReference<T> valueTypeRef)
        throws IOException {
        if (jsonText == null || "".equals(jsonText)) {
            return null;
        }
        //objMapper.enableDefaultTyping(); 
        return objMapper.readValue(jsonText, valueTypeRef);
    }

    public static <T> List<T> fromJson2List(String jsonText, Class<T> clazz)
            throws IOException{
        if (jsonText == null || "".equals(jsonText)) {
            return null;
        }
        List<T> objList = null;
        try {
            JavaType t = objMapper.getTypeFactory().constructParametricType(
                    List.class, clazz);
            objList = objMapper.readValue(jsonText, t);
        } catch (Exception e) {
        }
        return objList;
    }

    /**
     * 从json字符串中读取出指定的节点 <br>
     * 2015年1月27日:下午12:26:04<br>
     * <br>
     * 
     * @param json
     * @param key
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static JsonNode getValueFromJson(String json, String key)
        throws JsonProcessingException, IOException {
        JsonNode node = objMapper.readTree(json);
        return node.get(key);
    }

    /**
     * 从json字符串中读取数组节点所包含的JsonNode List<br>
     * 2015年1月28日:下午18:26:04<br>
     * <br>
     * 
     * @param json
     * @param key
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static List<JsonNode> getListFromJson(String json, String key)
        throws JsonProcessingException, IOException {
        List<JsonNode> jsonNodes = null;
        JsonNode node = objMapper.readTree(json);
        JsonNode arrayNode = node.get(key);
        if (arrayNode.isArray()) {
            jsonNodes = new ArrayList<JsonNode>();
            for (JsonNode jsonNode : arrayNode) {
                jsonNodes.add(jsonNode);
            }
        }
        return jsonNodes;
    }
    
    public static String map2Json(Map<String, Object> map) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }
    
    public static Map<String, String> json2Map(String json) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<Map<String, String>>() {});
    }

    public static void main(String[] args) throws Exception{
//         String jsonString
//         ="{\"fundType\":\"混合型\",\"isFavorite\":false,\"productId\":4629,\"isCurrency\":false,\"name\":\"金元顺安新经济主题混合\",\"value\":\"1.2710\",\"id\":5422,\"date\":\"09-09\",\"month\":\"-34.42%\",\"year\":\"-22.04%\",\"code\":\"620008\",\"range\":\"1.36%\",\"createDate\":\"2015-08-20\",\"month3\":\"-56.80%\",\"month6\":\"-28.66%\",\"year1\":\"10.50%\",\"week\":\"-2.26%\"}";
//         HotFundBeanRedis fromJson =fromJson(jsonString,
//         HotFundBeanRedis.class);
//         System.out.println(fromJson);
    }
}
