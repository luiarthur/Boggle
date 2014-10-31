#include <iostream>
#include <string>

using namespace std;

string condense(string input){
    
    string newInput;
    int size;
    int i = 0;
 
    size = input.size();
 
    do {
	if((input[i] > 64)&&(input[i] < 91))
	{
	  input[i] += 32;
	}
        if (isalpha(input[i]))
        {
          newInput += input[i];
        }
        i++;
 
    } while (i <= size - 1);
 
    return newInput;
}


