#pragma once

#include <string>
#include <iostream>
#include <vector>

using namespace std;

class Output {

 public:

  string word;
  vector<int> pos;
  
  Output(string w, vector<int> p) : word(w), pos(p) {}

  string getWord() const { return word; }
  vector<int> getPos() const { return pos; }

  void setWord(string word);
  void setPos(vector<int> pos);
  
  string getAll() const;
};


//ostream& operator<< (ostream& out, const Book& item);

