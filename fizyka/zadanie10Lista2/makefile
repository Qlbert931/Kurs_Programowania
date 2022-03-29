main: main.o Ball.o Progressbar.o MouseChangeableProgressbar.o
	g++ main.o Ball.o Progressbar.o MouseChangeableProgressbar.o -o main -lsfml-graphics -lsfml-window -lsfml-system

main.o: main.cpp Progressbar.hpp Ball.hpp MouseChangeableProgressbar.hpp
	g++ -c main.cpp -lsfml-graphics -lsfml-window -lsfml-system

Ball.o: Ball.cpp Ball.hpp
	g++ -c Ball.cpp -lsfml-graphics -lsfml-window -lsfml-system

Progressbar.o: Progressbar.cpp Progressbar.hpp
	g++ -c Progressbar.cpp -lsfml-graphics -lsfml-window -lsfml-system

MouseChangeableProgressbar.o: MouseChangeableProgressbar.cpp Progressbar.hpp MouseChangeableProgressbar.hpp
	g++ -c MouseChangeableProgressbar.cpp -lsfml-graphics -lsfml-window -lsfml-system

clear:
	rm *.o main