package pl.kolata.encoder;

import java.util.Properties;

/**
 * Created by Aleksander on 2017-06-28.
 */
public
    interface Encoder {

    void setEncodingProperties(Properties properties);

    String getCharacterUnicodeCode(char character);

    void setTextToEncode(String text);

    String encodeText();
}
