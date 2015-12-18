package com.exfp.files;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WebsiteGenerator {

	public static void main(String[] args) {
		Console c = System.console();
		
		System.out.print("Site name: ");
		String siteName = c.readLine();
		System.out.print("Author: ");
		String author = c.readLine();
		System.out.print("Do you want a folder for JavaScript? ");
		boolean wantJavaScript = c.readLine().toLowerCase().startsWith("y");
		System.out.print("Do you want a folder for CSS? ");
		Boolean wantCss = c.readLine().toLowerCase().startsWith("y");
		
		File appRoot = new File(".");
		File rootDir = new File(appRoot, siteName);
		File rootPage = new File(rootDir, "index.html");
		rootDir.mkdir();
		System.out.println("Created " + rootDir.getPath());
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rootPage))) {
			writer.write("<html>\n");
			writer.write("<head>\n");
			writer.write("<title>" + siteName + "</title>\n");
			writer.write("<meta>" + author + "</meta>\n");
			writer.write("</head>\n");
			writer.write("</html>\n");
			writer.close();
			System.out.println("Created " + rootPage.getPath());
		} catch (IOException e) {
			System.out.println("Error creating website");
		}
		if (wantJavaScript) {
			File jsFile = new File(rootDir, "js");
			jsFile.mkdir();
			System.out.println("Created " + jsFile.getPath());
		}
		if (wantCss) {
			File cssFile = new File(rootDir, "css");
			cssFile.mkdir();
			System.out.println("Created " + cssFile.getPath());
		}
		
	}

}
