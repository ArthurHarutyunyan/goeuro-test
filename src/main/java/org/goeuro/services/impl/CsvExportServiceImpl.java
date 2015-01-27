package org.goeuro.services.impl;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.goeuro.model.Result;
import org.goeuro.services.CsvExportService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * CSV Operation Service
 *
 * Created by arthurh on 1/28/15.
 */
public class CsvExportServiceImpl implements CsvExportService {

    /*
        field keep the information about csv file absolute path for creation
        if absolute path dose not filled it will save the scv in current directory where placed the jar file.
     */
    protected String csvFile;

    /**
     * @see org.goeuro.services.CsvExportService#exportCSVFile(java.util.Collection)
     */
    @Override
    public void exportCSVFile(Collection<Result> data) {

        //Define the Header
        ObjectWriter csvWriter = new CsvMapper().writer(
                CsvSchema.builder()
                        .setUseHeader(true)
                        .addColumn("_id")
                        .addColumn("name")
                        .addColumn("type")
                        .addColumn("latitude")
                        .addColumn("longitude")
                        .build());

        //Transform The Object Collection ccording defined headers.
        Collection<Object> csvtransformedData = Collections2.transform(
                data,
                new Function<Result, Object>() {
                    @Override
                    public Object apply(final Result entity) {
                        return new Object() {
                            public long _id = entity.get_id();
                            public String name = entity.getName();
                            public String type = entity.getType();
                            public double latitude = entity.getGeo_position().getLatitude();
                            public double longitude = entity.getGeo_position().getLongitude();
                        };
                    }
                }
        );

        //Create CSV file and export the data
        try {
            FileWriter writer = (new FileWriter(csvFile));
            String str = csvWriter.writeValueAsString(csvtransformedData);

            //Print data in console
            System.out.print(str);

            //write data into file
            writer.write(str);

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("File could not be created");
        }
    }

    public String getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(String csvFile) {
        this.csvFile = csvFile;
    }
}
