import heapq
import json
from time import time
from collections import deque

from auxiliar import dibujar_mapa_coloreado, generar_mapa_grafo

def realizar_voraz(mapa : dict):
    colores = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]
    solucion = dict()

    nodeList = bfs(mapa, 0)

    for key in nodeList:
        key = str(key)
        color = 0

        if key not in solucion:
            lista = mapa.get(key)

            availableColors = returnColorList(lista,colores,solucion)
            color = 0
            for i in range(len(availableColors)):
                if (availableColors[i]==False):
                    color = i
                    break
            if (color>=len(colores)):
                color=0
            solucion.update({key: colores[color]})
    
    return solucion

def returnColorList(lista, colores, solucion):
    visited = [False] * len(colores)
    colores2 = []
    for entry in lista:
        entry = str(entry)
        if entry in solucion:
            colores2.append(solucion.get(entry))
    
    i = 0
    for color in colores2:
        i = 0
        while i<len(colores):
            if color == colores[i]:
                if(visited[i]==False):
                    visited[i]=True
            i+=1
        
        
    return visited

def bfs(graph, start):
    visited = [False] * len(graph)
    queue = []
    queue.append(start)
    visited[start] = True
    order = []

    while queue:
        start = queue.pop(0)
        start = str(start)
        order.append(start)

        for i in graph.get(start):
            i = int(i)
            if not visited[i]:
                queue.append(i)
                visited[i] = True


    return order


def main_colorear_grafo(n):
    mapa = generar_mapa_grafo(n)
    solucion = realizar_voraz(mapa["grafo"])

    if solucion:
        print("Solución encontrada:", solucion)
        print("TIEMPO= MILISEGUNDOS")
        t1, t2 = 0, 0
        t1 = time()
        dibujar_mapa_coloreado(mapa, solucion)
        t2 = time()
        print("n= ", n, "**tiempo=", int(1000 * (t2 - t1)))

        with open('sols/solucion.json', 'w') as f:
                json.dump(solucion, f)
                f.close()
    else:
        print("No se encontró solución.")
        
if __name__ == "__main__":
    n = 4
    main_colorear_grafo(n)



