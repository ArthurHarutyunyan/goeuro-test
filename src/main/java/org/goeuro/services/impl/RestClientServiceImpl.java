package org.goeuro.services.impl;


import org.goeuro.model.Result;
import org.goeuro.services.RestClientService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @see org.goeuro.services.RestClientService
 *
 * Created by arthurh on 1/27/15.
 */

public class RestClientServiceImpl implements RestClientService {

    RestTemplate template = new RestTemplate();

    /**
     * parameter keep the information about endpoint
     */
    private String endPoint;

    public RestClientServiceImpl() {
        template = new RestTemplate();

        // Setup the RestTemplate configuration.

        List<HttpMessageConverter<?>> messageConverterList = template.getMessageConverters();

        // Set HTTP Message converter using a JSON implementation.
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();

        // Add supported media type returned by BI API.
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(new MediaType("text", "plain"));
        supportedMediaTypes.add(new MediaType("application", "json"));
        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverterList.add(jsonMessageConverter);
        template.setMessageConverters(messageConverterList);
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @see org.goeuro.services.RestClientService#getResultByCountry(String)
     * @param parameter - country code which will be provided to service as a argument
     * @return
     */
    @Override
    public Collection<Result> getResultByCountry(String parameter) {

        //form the url
        StringBuilder url = new StringBuilder(endPoint);
        url.append("/");
        url.append(parameter);

        //call to REST and extruct the data
        ResponseEntity<Result[]> res = template.getForEntity(url.toString(), Result[].class);

        return  Arrays.asList(
                res.getBody()
        );
    }
}
