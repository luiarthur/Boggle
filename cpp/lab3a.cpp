#include "print.h"
#include "readDictionary.h"
#include "output.h"
#include "boggle.h"


#include <set>
#include <iostream>
#include <algorithm>
#include <iomanip>
#include <sstream>
#include <fstream>

using namespace std;

int main (int argc, char* argv[]){
  
  set<string> boggleWords;
  set<string> dictionary;
  vector<string> letter;
  vector< vector<string> > board;

  ifstream in;
  string s;
  size_t side = 0;
  
  //readDictionary
  dictionary = readDictionary(argv[1]);

  //readLetters
  in.open(argv[2]);
  while (in >> s){
    letter.push_back(s);
  }
  in.close();
  //printSet(dictionary);
cout << dictionary.size()<<endl;


  //find side  
  while (letter.size() != side*side){
    side++;
  }
cout << side <<endl;

  //store into matrix
  for (size_t row =0; row < side; row++){
    vector <string> temp;
    for (size_t col =0; col < side; col++){
      temp.push_back(condense(letter[side*row+col]));
    }
    board.push_back(temp);
  }
  printVV(board);

  //fix here

  Boggle boggle (board,dictionary);
  

  for (size_t row =0; row < side; row++){
    for (size_t col =0; col < side; col++){
      vector <vector <bool> > visited;
      for (size_t r=0; r<side; r++){
        vector<bool> temp;
        for (size_t c=0; c<side; c++){
          temp.push_back(false);
        }
      visited.push_back(temp);
      }
      cout << "now searching" << " " << row+1 << col+1 <<endl;
      boggleWords = boggle.findw(row,col,visited,"",boggleWords,dictionary);
      cout << boggleWords.size() <<endl;
    }
  }


  cout << endl;
  outputSet(boggleWords,argv[3]);
  cout << endl;

}
