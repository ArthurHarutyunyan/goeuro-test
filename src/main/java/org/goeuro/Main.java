package org.goeuro;


import org.goeuro.model.Result;
import org.goeuro.services.CsvExportService;
import org.goeuro.services.RestClientService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;


/**
 * Main Application, Start point
 *
 *
 * Created by arthurh on 1/27/15.
 */
public class Main {
    public static void main(String... args) {

        if(args.length != 1) {
            System.err.println("Please add Country as parameter and enter only one value!");
            System.exit(1);
        }

        String country = args[0];


        //load Spring context
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("application-context.xml");

        //get ready service instances
        CsvExportService csvExportService = (CsvExportService) appContext.getBean("csvExportService");
        RestClientService restClientService = (RestClientService) appContext.getBean("restClientService");


        // invoke REST Servcie and prepare the data
        Collection<Result> result = restClientService.getResultByCountry(country);

        //save data into CSV file
        csvExportService.exportCSVFile(result);

    }

}
