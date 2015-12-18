package com.exfp.files;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.exfp.struct.PickWinner;

public class ProductSearch {
	
	private static final String CATALOG_FILENAME = "products.json";
	
	public static class CatalogItem {
		public String name;
		public double price;
		public long quantity;
	}

	public static void main(String[] args) {
		Console c = System.console();
		
		List<CatalogItem> catalog = readCatalog();
		
		SimpleEntry<String, List<CatalogItem>> hits = search(c, catalog);
		while (hits.getValue().size() < 1) {
			System.out.println("Sorry, that product was not found in our inventory.");
			if (PickWinner.prompt(c, "Would you like to add it to the catalog? ").toLowerCase().startsWith("y")) {
				addToCatalog(c, hits.getKey(), catalog);
			}
			hits = search(c, catalog);
		}
		
		hits.getValue().stream().forEach(item -> {
		    	   System.out.printf("\tName: %s\n\tPrice: $%.2f\n\tQuantity on hand: %d\n", item.name, item.price, item.quantity);				
		});
		
		System.exit(0);
	}

	private static List<CatalogItem> readCatalog() {
		JSONParser parser = new JSONParser();
		List<CatalogItem> catalog = new ArrayList<>();
		
		try (FileReader reader = new FileReader(CATALOG_FILENAME)) {
			JSONObject products = (JSONObject) parser.parse(reader);
			for (Object o : (JSONArray) products.get("products")) {
				JSONObject jo = (JSONObject) o;
				CatalogItem item = new CatalogItem();
				item.name = (String) jo.get("name");
				item.price = (Double) jo.get("price");
				item.quantity = (Long) jo.get("quantity");
				catalog.add(item);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not find JSON file");
			throw new RuntimeException(e);
		} catch (IOException e) {
			System.err.println("Error reading JSON file");
			throw new RuntimeException(e);
		} catch (ParseException e) {
			System.err.println("Malformed JSON at " + e.getPosition());
			System.err.println(e);
			throw new RuntimeException(e);
		}
		return catalog;
	}
	
	public static SimpleEntry<String, List<CatalogItem>> search(Console c, List<CatalogItem> catalog) {
		String searchTerm = PickWinner.prompt(c, "What is the product name? ");
		List<CatalogItem> hits = catalog.stream()
				                         .filter(item -> item.name.toLowerCase().contains(searchTerm.toLowerCase()))
				                         .collect(Collectors.toList());
		return new SimpleEntry<String, List<CatalogItem>>(searchTerm, hits);
		
	}

	public static void addToCatalog(Console c, String name, List<CatalogItem> catalog) {
		double price = Double.parseDouble(PickWinner.prompt(c, "Price? "));
		int quantity = Integer.parseInt(PickWinner.prompt(c, "Quantity? "));
		CatalogItem item = new CatalogItem();
		item.name = name;
		item.price = price;
		item.quantity = quantity;
		catalog.add(item);
		writeCatalog(catalog);
	}
	
	@SuppressWarnings("unchecked")
	public static void writeCatalog(List<CatalogItem> catalog) {
		try (FileWriter out = new FileWriter(CATALOG_FILENAME)) {
			JSONObject obj = new JSONObject();
			JSONArray products = new JSONArray();
			for (CatalogItem item : catalog) {
				Map<String, Object> jsonItem = new HashMap<>();
				jsonItem.put("name", item.name);
				jsonItem.put("price", item.price);
				jsonItem.put("quantity", item.quantity);
				products.add(jsonItem);
			}
			obj.put("products", products);
			obj.writeJSONString(out);
		} catch (IOException e) {
			System.err.println("Error writing updated product catalog");
		}
	}
}
