package com.example.lab5_matvii_sovhirenko_v2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    public static String getCurrencyRatesBaseUsd(InputStream stream) throws IOException {
        String result = new String();
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName("item");
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element rateNode = (Element) rateNodes.item(i);
                String currencyName = rateNode.getElementsByTagName("targetCurrency").item(0).getFirstChild().getNodeValue();
                String rate = rateNode.getElementsByTagName("exchangeRate").item(0).getFirstChild().getNodeValue();
                result += (String.format("%s – %s\n", currencyName, rate));
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCurrencyRatesByCurrency(InputStream stream, String currencyToSearch) throws IOException {
        String result = new String();
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName("item");
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element rateNode = (Element) rateNodes.item(i);
                String currencyName = rateNode.getElementsByTagName("targetCurrency").item(0).getFirstChild().getNodeValue();
                String rate = rateNode.getElementsByTagName("exchangeRate").item(0).getFirstChild().getNodeValue();

                if (currencyName.equalsIgnoreCase(currencyToSearch)) {
                    result += (String.format("%s – %s\n", currencyName, rate));
                }
            }
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
