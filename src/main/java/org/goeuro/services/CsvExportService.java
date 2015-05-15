package org.goeuro.services;

import org.goeuro.model.Result;

import java.util.Collection;

/**
 * CSV Operation Service
 * Service is responsible for CSV file creation
 *
 * Created by arthurh on 1/28/15.
 */
public interface CsvExportService {
    /**
     * create the CSV file and extruct the data into created file.
     * The CSV file headers have the following structure
     * _id, name, type, latitude, longitude
     *
     * @param data - dCSV data
     */
    public void exportCSVFile(Collection<Result> data);

    public String getCsvFile();
}
