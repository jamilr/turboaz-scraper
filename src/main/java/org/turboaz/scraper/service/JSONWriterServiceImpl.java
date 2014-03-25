package org.turboaz.scraper.service;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jr
 * Date: 3/24/14
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */

@Service("JsonWriter")
public class JSONWriterServiceImpl
        implements JSONWriterService {

    private static Logger logger = LoggerFactory.getLogger(JSONWriterService.class.getSimpleName());

    private ObjectMapper mapper;
    private File jsonFile;
    private FileOutputStream fOutStream;


    public JSONWriterServiceImpl(){
    }

    @PostConstruct
    protected void init() {
        mapper = new ObjectMapper();
    }

    @Override
    public void setFileName(String fileName) {

        jsonFile = new File(fileName);

        try {
            fOutStream = new FileOutputStream(jsonFile, true);
        } catch (FileNotFoundException fEx) {
            logger.error(fEx.getMessage());
        }
    }

    @Override
    public void write(Object object) {
        doWrite(object);
    }

    private void doWrite(Object obj) {

        try {

            String value = mapper.defaultPrettyPrintingWriter().writeValueAsString(obj);
            IOUtils.write(value, fOutStream);
            fOutStream.flush();
        } catch (JsonGenerationException jsonEx) {
            logger.error(jsonEx.getMessage());
        } catch (JsonMappingException mapEx) {
            logger.error(mapEx.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void close() {

        try {
            fOutStream.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }

    }
}
