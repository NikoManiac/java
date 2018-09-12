package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

import javax.jws.Oneway;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    List<Product> productList= new ArrayList<>();
    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        String readerString = "";
        int intValueOfChar;
        while ((intValueOfChar = reader.read()) != -1) {
            readerString += (char) intValueOfChar;
        }
        reader.close();

        ObjectMapper mapper = new ObjectMapper();

        productList = mapper.readValue(readerString.toString(), new TypeReference<List<Product>>() {});
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        StringBuilder frameTop = new StringBuilder();
        StringBuilder frameBody = new StringBuilder();
        StringBuilder frameBottom = new StringBuilder();

        int price = 0;
        String line = System.lineSeparator();

        frameTop.append("Receipts" + line);
        frameTop.append( "------------------------------------------------------------" + line);
        frameBottom.append( "------------------------------------------------------------" + line);
        if (barcodeContent == null || barcodeContent.length() == 0) {
            frameBottom.append( "Price: "+ price + line);
            return frameTop.toString() + frameBottom.toString();
        }

        List<String> barCodes = new ObjectMapper().readValue(barcodeContent, new TypeReference<List<String>>() {
        });
        Map<String, Integer> productNumber = new HashMap<>();

//        barCodes.stream().filter(item -> {
////            item.contains();
//        })
        List<Product> collect = productList.stream().filter(item -> {
            Product product = (Product) item;
            if (barCodes.contains(item.getId())) {
                if (productNumber.containsKey(item.getId())) {
                    productNumber.put(item.getId(), productNumber.get(item.getId()) + 1);
                } else {
                    productNumber.put(item.getId(), 1);
                }
            }
            return  barCodes.contains(((Product) item).getId());
        }).collect(Collectors.toList());

        for (Product product : collect) {
            String spaceStr = "";
            for (int i = 0; i < 32 -product.getName().length(); i++) {
                spaceStr += " ";
            }
            frameBody.append(product.getName() + spaceStr);
            spaceStr = "";
            for (int i = 0; i < 11 - product.getPrice().toString().length(); i++) {
                spaceStr += " ";
            }
            frameBody.append(product.getPrice() + spaceStr);
            frameBody.append(productNumber.get(product.getId()));
            frameBody.append("\n");

            price += product.getPrice();
        }

        frameBottom.append( "Price: "+ price + line);
        return frameTop.toString() + frameBody.toString() + frameBottom.toString();
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}