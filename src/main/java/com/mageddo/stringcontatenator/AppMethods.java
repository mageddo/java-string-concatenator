package com.mageddo.stringcontatenator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author elvis
 * @version $Revision: $<br/>
 *          $Id: $
 * @since 5/20/16 11:37 AM
 */
public class AppMethods {

    private AppMethods() {
    }

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    protected static String generateTextFromStringBuilder(final String sql, final String variableName) {
        Object result = groovy.util.Eval.me(String.format("%s;%s.toString();", sql, variableName));
        return result.toString();
    }

    protected static String generateJavaCode(final String inputStr, final String variableName, boolean isFinalVariable) {
        StringBuffer strBuilder = new StringBuffer();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStr.getBytes())));

            if(isFinalVariable){
                strBuilder.append("final ");
            }
            strBuilder.append(String.format("StringBuilder %s = new StringBuilder()", variableName));
            strBuilder.append(LINE_SEPARATOR);
            String line;
            while ((line = bf.readLine()) != null) {

                // scaping slashes
                line = line.replaceAll("\"", "\\\\\"");

                strBuilder.append("\t.append(\"");
                strBuilder.append(line);
                strBuilder.append(" \\n");
                strBuilder.append("\")");
                strBuilder.append(LINE_SEPARATOR);
            }
        } catch (IOException localIOException) {
        }
        return strBuilder.toString();
    }
}
