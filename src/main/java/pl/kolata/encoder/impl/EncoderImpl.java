package pl.kolata.encoder.impl;

import pl.kolata.encoder.Encoder;
import pl.kolata.exception.NoPropertiesFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
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

    protected Properties loadPropertiesFromResources(String propertiesFileName) throws IOException, NoPropertiesFoundException {

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
