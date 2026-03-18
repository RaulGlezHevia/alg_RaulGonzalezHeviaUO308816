import heapq
import json

from coloreado_grafo import main_colorear_grafo

if __name__ == "__main__":
    n = 8
    while n<65537:
        main_colorear_grafo(n)
        n=n*2
