main: main.o PrimeNumbers.o
	g++ main.o PrimeNumbers.o -o main

main.o: main.cpp Exceptions.hpp PrimeNumbers.hpp
	g++ -c main.cpp

PrimeNumbers.o: PrimeNumbers.cpp Exceptions.hpp PrimeNumbers.hpp
	g++ -c PrimeNumbers.cpp

clean:
	rm *.o main