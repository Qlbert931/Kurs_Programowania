main: main.o PascalTriangleRow.o
	g++ main.o PascalTriangleRow.o -o main

main.o: main.cpp PascalTriangleRow.hpp Exceptions.hpp
	g++ -c main.cpp

PascalTriangleRow.o: PascalTriangleRow.cpp PascalTriangleRow.hpp Exceptions.hpp
	g++ -c PascalTriangleRow.cpp

clear:
	rm *.o main