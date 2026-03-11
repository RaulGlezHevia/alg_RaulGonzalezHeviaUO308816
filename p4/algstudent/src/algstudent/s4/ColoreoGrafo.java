package algstudent.s4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ColoreoGrafo {
	
	// se busca utilizar el heurístico de minimo de colores posibles
	public static Map<String, String> realizarVoraz(Map<String, List<String>> grafo) {
		Map<String, String> mapaColores = new HashMap<String, String>();
		String[] colores = {"red", "blue", "green", 
		                    "yellow", "orange", "purple", "cyan", "magenta", "lime"};
		
		
		for (Entry<String, List<String>> nodo : grafo.entrySet()) {
			String key = nodo.getKey();
			
			if (!mapaColores.containsKey(key)) {
				List<String> nodosAdyacentes = nodo.getValue();
				int highestColor = 0;
				
				for (String nodoAdyacente : nodosAdyacentes) {
					int color = 0;
					
					if (mapaColores.containsKey(nodoAdyacente)) {
						String colorString = mapaColores.get(nodoAdyacente);
						
						while (!(colores[color]==colorString)) {
							color++;
						}
					}
					if (color>highestColor) {
						highestColor = color;
					}
					
				}
				
				mapaColores.put(key, colores[highestColor]);	
			}
		}
		
		
		
		return mapaColores;
	}

}
