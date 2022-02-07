package com.jac.javadb.util;

import java.util.List;

public class Utils {

	public static void stampaLista(List<Object> list) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("-----------------------------------------------------------\n");
		for(Object bean : list) {

			sb.append("|")
				.append(bean.toString())
				.append("|");
			
			sb.append("\n");
		}
		sb.append("-----------------------------------------------------------");	
		System.out.println(sb.toString());
		
	}
	
	
	public static void stampa(Object o) {
		o.toString();
	}

}
