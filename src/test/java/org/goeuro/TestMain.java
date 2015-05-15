package org.goeuro;


import org.goeuro.model.GeoPosition;
import org.goeuro.model.Result;
import org.goeuro.services.CsvExportService;
import org.goeuro.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by arthurh on 5/15/15.
 */
@Test
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TestMain extends AbstractTestNGSpringContextTests {

    @Autowired
    CsvExportService csvExportService;

    @Autowired
    RestClientService restClientService;

    Collection<Result> result;

    @Test()
    void testRestClient() {

        // invoke REST Servcie and prepare the data
        result = restClientService.getResultByCountry("Berlin");

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 6);


    }

    @Test()
    void testExportClient() {

        List<Result> resultList = new ArrayList<Result>();

        Result res1 = new Result();

        res1.set_id(376217);
        res1.setName("Berlin");
        res1.setType("location");
        GeoPosition geoPosition1 = new GeoPosition();
        geoPosition1.setLatitude(52.52437);
        geoPosition1.setLongitude(13.41053);
        res1.setGeo_position(geoPosition1);
        resultList.add(res1);

        Result res2 = new Result();

        res2.set_id(448103);
        res2.setName("Berlingo");
        res2.setType("location");
        GeoPosition geoPosition2 = new GeoPosition();
        geoPosition2.setLatitude(45.50298);
        geoPosition2.setLongitude(10.04366);
        res2.setGeo_position(geoPosition2);
        resultList.add(res2);

        csvExportService.exportCSVFile(resultList);

        File tmpFile = new File(csvExportService.getCsvFile());

        Assert.assertTrue(tmpFile.exists());
        tmpFile.delete();

    }

}
