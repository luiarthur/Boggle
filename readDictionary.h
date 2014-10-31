#include "condense.h"

#include <set>
#include <fstream>

using namespace std;

set<string> readDictionary (char* file) {
  
  ifstream in;
  set<string> S;
  string s;

  in.open(file);
  while(in >> s)
    if (s.size() >= 4)
      S.insert(condense(s));
  in.close();
  return S;
}
