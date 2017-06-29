package pl.kolata.encoder.impl;

import pl.kolata.encoder.Encoder;
import pl.kolata.exception.NoPropertiesFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Base implementation of Encoder
 * Created by Aleksander on 2017-06-28.
 */
public
    class EncoderImpl
    implements Encoder{

    protected Properties characterReplacements;
    protected String textToEncode;
    protected static final String UNICODE_SUFFIX = "\\u";

    @Override
    public void setEncodingProperties(Properties properties) {
        this.characterReplacements = properties;
    }

    @Override
    public String getCharacterUnicodeCode(char character) {
        return characterReplacements.getProperty(String.valueOf(character));
    }

    @Override
    public void setTextToEncode(String text) {
        this.textToEncode = text;
    }

    @Override
    public String encodeText() {
        return "";
    }

    /**
     * Method responsible for loading properties file from main/resources
     * @param propertiesFileName name of the properties file
     * @return Properties object created from file
     * @throws IOException when there was a problem during reading from file
     * @throws NoPropertiesFoundException when properties cannot be found
     */
    protected Properties loadPropertiesFromResources(String propertiesFileName)
            throws IOException, NoPropertiesFoundException {

        Properties properties = new Properties();
        InputStreamReader reader = null;


        try(InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName)){

            if(stream == null){
                throw new NoPropertiesFoundException("File with character encoding properties was not found",new NullPointerException());
            }

            reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
            properties.load(reader);

        }finally {
            if(reader != null){
                reader.close();
            }
        }

        return properties;
    }
}
