#include "output.h"

#include <fstream>
#include <iostream>
#include <string>
#include <set>    //1
#include <vector> //2

using namespace std;


//1
void printSet(set<string> items) {

  for (set<string>::iterator i = items.begin(); i != items.end(); i++) {
   string element = *i;
   cout << element <<endl;
  }

}

//2
void printVector(std::vector<std::string> items) {

  std::cout << '[';

  for (size_t i = 0; i < items.size(); i++) {
    if (i > 0)
      std::cout << ", ";
    std::cout << '\'' << items.at(i) << '\'';
  }

  std::cout << ']' << std::endl;

}

//3
void printOutputV(vector<Output> items, char* dest) {
  
  ofstream out;
  out.open(dest);
  for (size_t i=0; i < items.size(); i++){
    out << items[i].getAll() <<endl;
  }
  out.close();

}


//4
void printV(std::vector<std::string> items) {

  for (size_t i = 0; i < items.size(); i++) {
    std::cout << items.at(i) << ' ';
  }

}
//5
void printVV(std::vector< vector<std::string> >items) {
  for (size_t i = 0; i < items.size(); i++) {
    printV(items[i]);
    cout <<endl;
  }
}

//6
void outputSet(set<string> items, char* dest) {
  
  ofstream out;
  out.open(dest);
  for (set<string>::iterator i = items.begin(); i != items.end(); i++) {
   string element = *i;
   out << element <<endl;
  }
  out.close();

}
