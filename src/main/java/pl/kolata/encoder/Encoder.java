package pl.kolata.encoder;

import java.util.Properties;

/**
 * Interface responsible for basic operations of text encoding class
 * Created by Aleksander on 2017-06-28.
 */
public
    interface Encoder {

    /**
     * Set the value of properties file, with character=code values
     * @param properties
     */
    void setEncodingProperties(Properties properties);

    /**
     * Return the unicode code for given character
     * @param character character
     * @return characters unicode code or null if not found
     */
    String getCharacterUnicodeCode(String character);

    void setTextToEncode(String text);

    /**
     * Responsible for encoding the text
     * @return text with encoded characters
     */
    String encodeText();
}
