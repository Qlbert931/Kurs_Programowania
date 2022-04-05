cmake_minimum_required(VERSION 3.2.0 FATAL_ERROR)
project(zadanie2)

include_directories(inc)
include_directories(src)

file(GLOB_RECURSE SRC "src/*.cpp")

set (CMAKE_CXX_FLAGS)

add_executable(main ${SRC})
set_property(TARGET main PROPERTY CXX_STANDARD 20)