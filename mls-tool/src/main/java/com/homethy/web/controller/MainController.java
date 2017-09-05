package com.homethy.web.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.GetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.homethy.web.domain.ResourceBean;
import com.homethy.web.domain.ResourceSchemeBean;
import com.homethy.web.domain.RetsM;
import com.homethy.web.service.IListingDataBeanSerivce;
import com.homethy.web.service.IRetsMService;
import com.homethy.web.service.ResourceService;
import com.homethy.web.utils.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;



@Controller
public class MainController {

  private Log log = LogFactory.getLog(MainController.class);

  public static final String DYNAMODB_TABLE_LISTING = "listing";
  public static final String DYNAMODB_TABLE_LISTING_HASHKEY = "chimeKey";



  @Autowired
  IListingDataBeanSerivce listingDataBeanSerivce;


  @Autowired
  IRetsMService retsMService;

  @Autowired
  ResourceService resourceService;

  @RequestMapping("/")
  public ModelAndView toMain() {
    ModelAndView view = new ModelAndView("main");
    List<RetsM> rms = retsMService.getRetsMList();
    view.addObject("mls_meta", rms);
    return view;
  }

  @RequestMapping("/{mlsId}")
  public ModelAndView toIndex(@PathVariable int mlsId) throws IOException {
    System.out.println(1);
    ModelAndView view = new ModelAndView("mlsId");
    String lCols = listingDataBeanSerivce.getGridListingDataBean(mlsId);
    String mlsMeta = retsMService.getRetsMByMlsId(mlsId);
    view.addObject("mls_meta", mlsMeta);
    view.addObject("listing_cols", lCols);
    view.addObject("mlsId", mlsId);

    return view;
  }

  @RequestMapping("/save/{mlsId}")
  public @ResponseBody String saveText(@RequestBody String parserData, @PathVariable int mlsId) {
    retsMService.updateParserData(parserData, mlsId);
    return "success";
  }

  /**
   * 将记录写入文件
   *
   * @param fileName 文件名
   * @param content 写入的内容
   * @throws IOException 文件读取写入失败抛出异常
   */
  @SuppressWarnings("unused")
  private static void writeFile(String fileName, String content) throws IOException {
    File file = new File(fileName);
    System.out.println(file.getAbsolutePath());
    if (!file.exists())
      file.createNewFile();
    BufferedWriter writer = new BufferedWriter(new FileWriter(file.getName(), true));
    writer.write(content);
    writer.write("\n");
    writer.flush();
    writer.close();
  }

  @RequestMapping("/saveResource")
  public @ResponseBody String saveResource(@RequestParam int mlsId, @RequestParam String resource,
      @RequestParam String classes) {
    String result = "";
    BufferedReader in = null;
    try {
      String url = "http://predatastore.chime.me/config/mls-info/resource";
      String urlNameString =
          url + "?mlsId=" + mlsId + "&resourceName=" + resource + "&className=" + classes;
      // System.out.println(urlNameString);
      log.info("Get Url:" + urlNameString);
      URL realUrl = new URL(urlNameString);
      // 打开和URL之间的连接
      URLConnection connection = realUrl.openConnection();
      // 设置通用的请求属性
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent",
          "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 建立实际的连接
      connection.connect();
      // 获取所有响应头字段
      Map<String, List<String>> map = connection.getHeaderFields();
      // 遍历所有的响应头字段
      for (String key : map.keySet()) {
        System.out.println(key + "--->" + map.get(key));
      }
      // 定义 BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println("发送GET请求出现异常！" + e);
      log.error(e.getMessage(), e);
      e.printStackTrace();
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    List<ResourceBean> list =
        JSONArray.toList(JSONArray.fromObject(result), new ResourceBean(), new JsonConfig());
    resourceService.deleteMlsResource(mlsId);
    for (ResourceBean bean : list) {
      String chimeKey = String.format("%s.%s.%s.%s", bean.getMlsId(), bean.getResourceName(),
          bean.getClassName(), bean.getResourceKey());
      log.info("Get data from dynamodb, chimeKey =" + chimeKey);
      bean.setData(getDynamodbData(chimeKey));
      resourceService.insertResource(bean);
    }
    return "success";
  }

  private String getDynamodbData(String chimeKey) {
    try {
      Table table = getDynamoDbInstance().getTable(DYNAMODB_TABLE_LISTING);
      GetItemOutcome outcome = table.getItemOutcome(DYNAMODB_TABLE_LISTING_HASHKEY, chimeKey);
      Item item = outcome.getItem();
      return item.toJSON();
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage(), e);
    }
    return "";
  }

  @RequestMapping(value = "/get/sample-data", method = RequestMethod.POST)
  @ResponseBody
  public String getSampleData(@RequestParam(value = "mlsId") int mlsId,
      @RequestParam(value = "res") String res, @RequestParam(value = "clazz") String clazz) {
    ObjectNode datas = JsonUtil.getObjectNode();
    List<ResourceBean> list = resourceService.getResourceList(mlsId, res, clazz, 10);
    int i = 1;
    for (ResourceBean bean : list) {
      JsonNode data = JsonUtil.toJsonNode(bean.getData());
      if (null != data) {
        datas.set(String.valueOf(i++), data);
      }
    }
    return datas.toString();
  }

  @RequestMapping(value = "/insert/new-mls", method = RequestMethod.POST)
  @ResponseBody
  public String insertNewMls(@RequestParam(value = "mlsId") int mlsId) {
    RetsM rm = new RetsM();
    rm.setMlsOrgId(mlsId);
    retsMService.insert(rm);
    return "done";
  }

  private static DynamoDB docClient = null;

  private static synchronized DynamoDB getDynamoDbInstance() {
    if (docClient == null) {
      docClient = new DynamoDB(AmazonDynamoDBClientBuilder.standard().build());
    }
    return docClient;
  }

  @RequestMapping("/getRets")
  public @ResponseBody String getRets(@RequestParam int mlsId, @RequestParam int index)
      throws IOException {
    RetsM retsM = retsMService.get(mlsId);
    retsM.setCreateTime(null);
    retsM.setUpdateTime(null);

    String param = JSONObject.fromObject(retsM).toString();
    String url = "";
    if (index == 0)
      url = "http://predatastore.chime.me/config/mls-info/retsmd";
    else if (index == 1)
      url = "https://datastore.chime.me/config/mls-info/retsmd";
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      URLConnection conn = realUrl.openConnection();
      // 设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent",
          "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 发送POST请求必须设置如下两行
      conn.setDoOutput(true);
      conn.setDoInput(true);
      // 获取URLConnection对象对应的输出流
      out = new PrintWriter(conn.getOutputStream());
      // 发送请求参数
      out.print(param);
      // flush输出流的缓冲
      out.flush();
      // 定义BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println("发送 POST 请求出现异常！" + e);
      e.printStackTrace();
    }
    // 使用finally块来关闭输出流、输入流
    finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return "success";
  }
  
  @RequestMapping("/saveResourceById")
  public @ResponseBody String saveResourceById(@RequestParam int mlsId) {
	List<ResourceSchemeBean> resSchems = readSchemes(String.valueOf(mlsId));
	List<String> urlList = new ArrayList<String>();
	String url = "https://datastore.chime.me/config/mls-info/resource";
	for(ResourceSchemeBean resourceSchemeBean : resSchems) {
		String classes = "";
		ArrayList<String> list = resourceSchemeBean.getClassName();
		for(String i : list){
			classes += i+";";
		}
		String urlNameString =
	              url + "?mlsId=" + mlsId + "&resourceName=" + resourceSchemeBean.getResourceName() + "&className=" + classes;
		urlList.add(urlNameString);
	}
   
    BufferedReader in = null;
    resourceService.deleteMlsResource(mlsId);
    for(String urls : urlList){
    	String result = "";
	    try {
	      System.out.println(urls);
	      log.info("Get Url:" + urls);
	      URL realUrl = new URL(urls);
	      // 打开和URL之间的连接
	      URLConnection connection = realUrl.openConnection();
	      // 设置通用的请求属性
	      connection.setRequestProperty("accept", "*/*");
	      connection.setRequestProperty("connection", "Keep-Alive");
	      connection.setRequestProperty("user-agent",
	          "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	      // 建立实际的连接
	      connection.connect();
	      // 获取所有响应头字段
	      Map<String, List<String>> map = connection.getHeaderFields();
	      // 遍历所有的响应头字段
	      for (String key : map.keySet()) {
	        System.out.println(key + "--->" + map.get(key));
	      }
	      // 定义 BufferedReader输入流来读取URL的响应
	      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      String line;
	      while ((line = in.readLine()) != null) {
	        result += line;
	      }
	      Thread.sleep(500);
	    } catch (Exception e) {
	      System.out.println("发送GET请求出现异常！" + e);
	      log.error(e.getMessage(), e);
	      e.printStackTrace();
	    } finally {
	      try {
	        if (in != null) {
	          in.close();
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    }
	    List<ResourceBean> list =
	        JSONArray.toList(JSONArray.fromObject(result), new ResourceBean(), new JsonConfig());
	    for (ResourceBean bean : list) {
	      String chimeKey = String.format("%s.%s.%s.%s", bean.getMlsId(), bean.getResourceName(),
	          bean.getClassName(), bean.getResourceKey());
	      log.info("Get data from dynamodb, chimeKey =" + chimeKey);
	      bean.setData(getDynamodbData(chimeKey));
	      resourceService.insertResource(bean);
	    }
	  }
    return "success";
  }
  
  @RequestMapping("/saveResourceBySql")
  public @ResponseBody String saveResourceBySql(@RequestParam int mlsId,@RequestParam int sql) {
	  	String result = "";
	    BufferedReader in = null;
	    try {
	      String url = "https://datastore.chime.me/config/mls-info/resource";
	      String urlNameString =
	          url + "?mlsId=" + mlsId + "&sql=" + sql;
	      // System.out.println(urlNameString);
	      log.info("Get Url:" + urlNameString);
	      URL realUrl = new URL(urlNameString);
	      // 打开和URL之间的连接
	      URLConnection connection = realUrl.openConnection();
	      // 设置通用的请求属性
	      connection.setRequestProperty("accept", "*/*");
	      connection.setRequestProperty("connection", "Keep-Alive");
	      connection.setRequestProperty("user-agent",
	          "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	      // 建立实际的连接
	      connection.connect();
	      // 获取所有响应头字段
	      Map<String, List<String>> map = connection.getHeaderFields();
	      // 遍历所有的响应头字段
	      for (String key : map.keySet()) {
	        System.out.println(key + "--->" + map.get(key));
	      }
	      // 定义 BufferedReader输入流来读取URL的响应
	      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	      String line;
	      while ((line = in.readLine()) != null) {
	        result += line;
	      }
	    } catch (Exception e) {
	      System.out.println("发送GET请求出现异常！" + e);
	      log.error(e.getMessage(), e);
	      e.printStackTrace();
	    } finally {
	      try {
	        if (in != null) {
	          in.close();
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    }
	    List<ResourceBean> list =
	        JSONArray.toList(JSONArray.fromObject(result), new ResourceBean(), new JsonConfig());
	    resourceService.deleteMlsResource(mlsId);
	    for (ResourceBean bean : list) {
	      String chimeKey = String.format("%s.%s.%s.%s", bean.getMlsId(), bean.getResourceName(),
	          bean.getClassName(), bean.getResourceKey());
	      log.info("Get data from dynamodb, chimeKey =" + chimeKey);
	      bean.setData(getDynamodbData(chimeKey));
	      resourceService.insertResource(bean);
	    }
	    return "success";
  }
  
  List<ResourceSchemeBean> readSchemes(String mlsId) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB docClient = new DynamoDB(client);

		Table table = docClient.getTable("mls");
		GetItemOutcome outcome = table.getItemOutcome(
				"id", Integer.parseInt(mlsId));
		String jsonStr = outcome.getItem().getJSON("scheme");

		ObjectMapper objMap = new ObjectMapper();

		List<ResourceSchemeBean> schemes = null;
		try {
			schemes = objMap.readValue(jsonStr, new TypeReference<ArrayList<ResourceSchemeBean>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return schemes;
	}
}
