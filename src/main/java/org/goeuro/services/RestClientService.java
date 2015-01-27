package org.goeuro.services;

import org.goeuro.model.Result;
import java.util.Collection;


/**
 * Service was responsible for REST api communication.
 *
 * Service is use the Spring RestTemplate approach.
 *
 * Created by arthurh on 1/28/15.
 */

public interface RestClientService {

    /**
     * method return the formed Result list after REST service call
     *
     * @param parameter - country code which will be provided to service as a argument
     * @return Collection<Result>
     */
    public Collection<Result> getResultByCountry(String parameter);
}
