#include <string>
#include "boggle.h"
#include "Set_Functions.h"
#include <vector>
#include <set>
#include <algorithm>


using namespace std;

void Boggle::setBoard(vector < vector <string> > board) {
  this->board = board;
}
void Boggle::setDi(set<string> di) {
  this->di = di;
}

set<string> foundPrefix (string s, set<string> S){


  set<string> newDi;
  for (set<string>::iterator i = S.lower_bound (s); i != S.end(); i++) { // for each element in the set
    string element = *i;
    if ( element.substr(0,s.size()) == s ){
      newDi.insert(element);
    }
    else break;
  }
  return newDi;
}

set<string> Boggle::findw(size_t r, size_t c, vector< vector<bool> > visited, string word, set<string> bgW, set<string> dic) const {

  if ( (r < 0) || (c < 0) || (r >= board.size()) || (c >= board.size()) || visited[r][c] )  {return bgW;} // OOB
    else if ( foundPrefix(word+board[r][c],dic).empty() ) {visited[r][c] = true; return bgW;} //dead end
      else {
            word += board[r][c];
            visited[r][c] = true;
            set<string> temp = foundPrefix(word,dic);           

            if ( temp.find(word) != temp.end() ){bgW.insert(word);}
              return (  (findw(r,c+1,  visited,word,bgW,temp))
                     +  (findw(r,c-1,  visited,word,bgW,temp))
                     +  (findw(r+1,c,  visited,word,bgW,temp))
                     +  (findw(r-1,c,  visited,word,bgW,temp))
                     +  (findw(r+1,c+1,visited,word,bgW,temp))
                     +  (findw(r-1,c+1,visited,word,bgW,temp))
                     +  (findw(r-1,c-1,visited,word,bgW,temp))
                     +  (findw(r+1,c-1,visited,word,bgW,temp)) );
           }
}
