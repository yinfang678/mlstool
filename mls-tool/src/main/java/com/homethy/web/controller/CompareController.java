package com.homethy.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.homethy.web.domain.CompareDataBean;
import com.homethy.web.domain.Databean;
import com.homethy.web.domain.ListingDataBean;
import com.homethy.web.service.ListingCompareService;
import com.homethy.web.service.ListingDataService;
import com.homethy.web.utils.JacksonUtils;

@Controller
public class CompareController {
	
	@Autowired
	ListingCompareService compareService;
	
	@Autowired
	ListingDataService listingDataService;
	
	@RequestMapping("/compareTest")
	public @ResponseBody String compareTest(@RequestParam int mlsId) throws JsonParseException, JsonMappingException, IOException {
		List<CompareDataBean> list = compareService.getCompareList(mlsId);
		for(CompareDataBean comparebean:list){
			if(StringUtils.isNotEmpty(comparebean.getMlsData())){
				Map<String, String> resultMap = JacksonUtils.json2Map(comparebean.getMlsData());
				ListingDataBean dataBean = new ListingDataBean();
				dataBean.setMlsListingId(comparebean.getMlsListingId());
				dataBean.setMlsOrgId(comparebean.getMlsOrgId());
				dataBean.setPropertyType(resultMap.get("Type"));
				dataBean.setBedrooms(safeGetInt(resultMap.get("Beds")));
				dataBean.setSqft(safeGetInt(resultMap.get("sqft_t")));
				dataBean.setDataResource("Zillow");
				dataBean.formatInfo();
				ListingDataBean oldListing = listingDataService.selectListingInfo(comparebean.getMlsOrgId(), comparebean.getMlsListingId());
				if(oldListing == null){
					listingDataService.insertListingInfo(dataBean);
				}else{
					listingDataService.updateListingInfo(dataBean);
				}
			}
		}
		System.out.println(list.size());
		return "success";
	}
	
	public static String getOnlyNumbers(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static int safeGetInt(String str) {
		int intNumber = 0;
		try {
			if (str != null) {
				String numberStr = null;
				if (str.indexOf('.') != -1) {
					String intPart = str.split("\\.")[0];
					numberStr = getOnlyNumbers(intPart);
				} else {
					numberStr = getOnlyNumbers(str);
				}
				intNumber = numberStr.isEmpty() ? 0 : Integer.valueOf(numberStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intNumber;
	}

	public static float safeGetFloat(String str) {
		try {
			return Float.valueOf(str);
		} catch (Exception e) {
		}
		return 0;
	}

	public static Double safeGetDouble(String str) {
		try {
			return Double.valueOf(str);
		} catch (Exception e) {
		}
		return 0d;
	}

	public static String safeToString(Object obj) {
		return obj != null ? obj.toString().trim() : "";
	}
}
