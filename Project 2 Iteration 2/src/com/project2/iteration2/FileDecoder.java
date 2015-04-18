package com.project2.iteration2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Nick Clarity
 * Project 2 Iteration 2
 * Apr 17, 2015
 * 
 * This class facilitates parsing the configuration parameters from the text file.
 */
public class FileDecoder{
	private static FileDecoder fileDecoder;
	private static String path;
	private static int[] config;
	
	private FileDecoder(){}
	
	/**
	 * For Singleton Patter
	 * @return The only instance of FileDecoder
	 */
	public static FileDecoder instance(){
		if(fileDecoder == null){
			fileDecoder = new FileDecoder();
		}
		
		return fileDecoder;
	}
	
	/**
	 * Accepts a path to the config file and turns it into an array of ints
	 * 
	 * @param path the location and name of the config file
	 * @return an array of ints containing the config values
	 * @throws FileNotFoundException if there is no file found at the path provided.
	 */
	public int[] parseFile(String path) throws FileNotFoundException{
		File file = new File(path);
		Scanner scan = new Scanner(file);

		config = new int[14];
		int i = 0;
		
		while(scan.hasNext()){
			config[i] = scan.nextInt();
			i++;
		}

		return config;
	}
}