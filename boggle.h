#pragma once

#include <set>
#include <iostream>
#include <string>
#include <vector>


using namespace std;

class Boggle {

 public:
  vector < vector <string> > board;
  set<string> di;

  Boggle(vector < vector <string> > b, set<string> d) : board(b), di(d){}

  vector < vector <string> > getBoard() const { return board; }
  set<string> getDi() const {return di;}

  void setBoard(vector < vector <string> > board);
  void setDi(set<string> di);

  
  set<string> findw(size_t r, size_t c, vector< vector<bool> > visited, string word, set<string> bgW, set<string> dic) const;
  //set<string> findw(size_t r, size_t c) const;
};




