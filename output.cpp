#include "output.h"

#include <sstream>

using namespace std;

void Output::setWord(string word) {
  this->word = word;
}
void Output::setPos(vector<int> pos) {
  this->pos = pos;
}

string Output::getAll() const {
  stringstream out;
  string s = " ";
  for(size_t i = 0; i < pos.size(); i++){
     out << pos.at(i);
     s += " "+out.str();
     out.str(std::string());//clears out. Otherwise out will stack.
  }

  return word +":"+ s;
}

