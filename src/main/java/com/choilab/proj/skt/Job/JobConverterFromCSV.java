package com.choilab.proj.skt.Job;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class JobConverterFromCSV {

	public static ArrayList<Job> read(String filename) {
		try {
			ArrayList<Job> list = new ArrayList<Job>();
			BufferedReader br = new BufferedReader(new FileReader(filename));
			br.readLine();
			
			String line;
			while((line = br.readLine()) != null){
				String[] temp = line.split(",");
				
				double txLoss = Double.parseDouble(temp[0]);
				double txDelay = Double.parseDouble(temp[1]);
				double txJitter= Double.parseDouble(temp[2]);
				
				double rxLoss = Double.parseDouble(temp[3]);
				double rxDelay = Double.parseDouble(temp[4]);
				double rxJitter= Double.parseDouble(temp[5]);
				list.add(new Job(new NS3Data(txLoss,txDelay,txJitter, rxLoss, rxDelay, rxJitter)));
			}
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
