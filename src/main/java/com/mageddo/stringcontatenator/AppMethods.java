package com.mageddo.stringcontatenator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

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

    protected String generateSqlCode(String sql) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("java");
        try {
            Object result = engine.eval(sql + "sql.toString();");
            System.out.println(result);
        } catch (ScriptException e1) {
            e1.printStackTrace();
        }
        return "oi :/";
    }

    protected static String generateJavaCode(String inputStr) {
        StringBuffer strBuilder = new StringBuffer();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(inputStr.getBytes())));

            strBuilder.append("StringBuilder str = new StringBuilder();");
            strBuilder.append(LINE_SEPARATOR);
            String line;
            while ((line = bf.readLine()) != null) {

                // scaping aspas
                line = line.replaceAll("\"", "\\\\\"");

                strBuilder.append("str.append(\"");
                strBuilder.append(line);
                strBuilder.append(" \\n");
                strBuilder.append("\");");
                strBuilder.append(LINE_SEPARATOR);
            }
        } catch (IOException localIOException) {
        }
        return strBuilder.toString();
    }
}
