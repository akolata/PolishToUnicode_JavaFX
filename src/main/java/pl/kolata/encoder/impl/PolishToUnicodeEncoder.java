package pl.kolata.encoder.impl;

import pl.kolata.exception.NoPropertiesFoundException;
import java.io.*;

/**
 * Concrete implementation of Encoder
 * Responsible for encoding from polish characters to unicode
 * Created by Aleksander on 2017-06-28.
 */
public
    class PolishToUnicodeEncoder
    extends EncoderImpl{

    private final String POLISH_CODES_FILE = "polish_codes.properties";


    public PolishToUnicodeEncoder() throws IOException, NoPropertiesFoundException {
        setEncodingProperties(loadPropertiesFromResources(POLISH_CODES_FILE));
    }

    @Override
    public String encodeText() {

        for(String character : characterReplacements.stringPropertyNames()){
            textToEncode = textToEncode.replace(character,
                    UNICODE_SUFFIX + getCharacterUnicodeCode(character));
        }

        return textToEncode;
    }
}
