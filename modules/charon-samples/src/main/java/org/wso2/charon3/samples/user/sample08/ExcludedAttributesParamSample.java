package org.wso2.charon3.samples.user.sample08;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * SCIM excludedAttributes usage sample.
 */
public class ExcludedAttributesParamSample {

    private static final Logger logger = LoggerFactory.getLogger(ExcludedAttributesParamSample.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        //get the id of the user
        Scanner reader = new Scanner(System.in, "UTF-8");  // Reading from System.in
        System.out.print("Enter comma separated excludedAttributes param list : ");;
        String excludedAttributes = reader.next();

        try {
            String url = "http://localhost:8080/scim/v2/Users?excludedAttributes=" + excludedAttributes.trim();
            URL obj = new URL(url);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();

            BufferedReader in;
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                in = new BufferedReader(new InputStreamReader(
                        con.getInputStream(), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        con.getErrorStream(), "UTF-8"));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //printing result from response
            logger.info("Response Code : " + responseCode);
            logger.info("Response Message : " + con.getResponseMessage());
            logger.info("Response Content : " + response.toString());


        } catch (ProtocolException e) {
            logger.error(e.getMessage());
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

