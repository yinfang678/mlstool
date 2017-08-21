package com.homethy.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class ListingDataBeanSerivceTest {

  @Autowired
  private IListingDataBeanSerivce listingDataBeanSerivce;

  @Test
  public void testGetListingDataBean() {
    // Assert.notNull(listingDataBeanSerivce.getListingDataBean(), "read json failed");
  }

  @Test
  public void testGridListingDataBean() {
    // Assert.notNull(listingDataBeanSerivce.getGridListingDataBean(10), "to grid failed");
  }

  @Test
  public void testGridJsonToParseJson() {
    String girdJson = listingDataBeanSerivce.getGridListingDataBean(10);
    // Assert.notNull(listingDataBeanSerivce.gridJsonToParseJson(girdJson), "grid to parse failed");
  }
}
