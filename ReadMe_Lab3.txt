
Computer Science 235 :: Data Structures and Algorithms
Boggle

Note: Projects are to be completed by each student individually (not by groups of students).

The objective of this project is to design and implement the data structures and algorithms necessary for a computer player to play the popular board game Boggle.
What is Boggle?

The game of Boggle is played with a set of sixteen letter cubes, which are standard six-sided dice except that they are marked with letters of the alphabet instead of numbers. The cubes are rolled and arranged into a 4x4 square that might look like this:

EECA
ALEP
HNBO
QTTY

The object of the game is to start at one cube and then work through a chain of letters to form a word that meets the following conditions:

    The word must be at least four letters long.
    The path traced out by the letters in the word must be connected horizontally, vertically, or diagonally. You can't skip over intervening cubes to get the next letter.
    Each cube may be used only once in a given word. 

For example, the sample board contains the word PEACE as follows:

EECA
ALEP
HNBO
QTTY

The board, however, does not contain the word PLACE which would require jumping from the P to the L and then back to the A . Similarly, it is not possible to make the word POPE , because doing so would require reusing the P .

The Boggle player you create will find and output all the valid words on a given board. Valid words are those listed in a given dictionary.
Example Inputs

The Boggle player is given a dictionary and a board configuration as inputs.
Dictionary

blue
boot
booth
cat
dog
orbit
riot
robot
root
run

Board


R B 
 O O I

T  R O H 

Example Output

The Boggle player outputs the board in a standard format followed by the set of the words found on the board.

r b o
o i t
r o h

boot
booth
orbit
riot
robot
root

Testing

Here are some ideas for tests.

    An empty dictionary file.
    Boards of various sizes such as 9, 16, and 25.
    A board with a word that is shorter than length 4.
    A board with a word made by reusing tiles.
    A board with a word made by skipping tiles. 

Dictionary File Format

The dictionary is a text file that contains a list of words, one word per line. When comparing words on the board with words in the dictionary, use a comparison that is not case-sensitve.
Boggle Board Files

The Boggle board is a text file that contains a list of strings separated by white space. Each string gives the letter(s) for one of the tiles on the Boggle board.

The number of strings in the file will always be a perfect square such as 9, 16, or 25. (The board will always be square.) In standard Boggle the number of tiles is 16 (a 4x4 board). Your Boggle player needs to work correctly with a square board of any size.

The strings in the file are organized by row. The strings for the first board-row are given first followed by the strings for the second row. The strings for the last row on the board come last. Within each row the strings are listed from left to right. The first string in the file gives the letter(s) on the tile at the upper left corner of the board. The last string in the file gives the letter(s) on the tile at the bottom right corner of the board.

Line breaks may appear in the board file between any two tile strings. They do not always occur at the end of a board-row.

Each string in the board file may be upper or lower case and may contain one or more letters. In official Boggle, each tile contains only one letter, (except for one tile that contains 'Qu'). Your Boggle player needs to work correctly with strings of any length.
Output Format

The output consists of the board that was read from the input followed by the set of words found on the board. There is a single blank line between the board output and the set of found words. This blank line is a very important part of the output. It is used to separate the two parts of the output. (There are no blank lines within the board output and there are no blank lines within the set of words.)
Board Output Format

The tiles on the board are output in the same order they were read from the input. Each row of tiles on the board is output on a separate line. There is a line-break in the output at the end of each board-row. There are no line-breaks in the middle of a board-row. Each tile on a board-row is separated from the previous tile on the row by a single space. The tiles on the board are output in lower case.
Found Words Output Format

The words found on the Boggle board are output one word per line. Each word given in the output must be:

    found in the dictionary
    at least the minimum length of 4 letters
    found on the board following the standard Boggle rules. (Each tile can participate at most once in a given word.) 

The words are output in sorted order.

The words are output in lower case.

Each word is only output once even though the board may contain the same word in multiple locations.
Dictionary Operations

If Boggle is to run in a reasonable amount of time, the operations for searching the dictionary must be fast, even for large dictionaries.

One way to make the search fast is to store the dictionary in a Set and use the Set find operation to search for a word. The find operation for a Set is typically a fast operation.

Another way to make the search fast is to store the dictionary in a sorted List and use a binary search algorithm to search for a word. You would need to use a type of List that supports efficient indexing (such as a vector).

When searching the Boggle board for words you will need to stop following a path whenever you can determine that the first part of the path does not match a prefix of any word in the dictionary. Thus you need to be able to search the dictionary for prefixes and the prefix search must be fast.

If the dictionary is stored in a Set, many Sets provide a way to find the string in the Set that is closest to the one you are searching for. Such an operation could be used to quickly find a word with a given prefix.

If the dictionary is stored in a sorted List, the binary search algorithm can be modified to find the string in the List that is closest to the one you are searching for.
Word Search Operations

You are required to find the words on the board using a recursive search over the paths on the board. Of course, there are many paths to be followed. If Boggle is to run in a reasonable amount of time, your algorithm will need to be intelligent about avoiding wasted effort. One way to speed up the search is to only follow paths that have the possibility of leading to valid words. Before moving along a path, the search algorithm should ask the dictionary if any words begin with the prefix represented by the current path. If the answer is no, there is no need to follow the path further. Checking prefixes in this manner will allow your algorithm to avoid many dead-end paths, and therefore run more quickly. In fact, if you don't do this, your program will run too slowly to pass off.
Implementation Requirements

    Store the dictionary in a Set or a sorted List.
    Use either a set operation or binary search to locate a word in the dictionary that is closest to a given prefix.
    Use a recursive search over the contents of the board to find the words.
    Use a compare that is not case-sensitive when comparing words on the board with words in the dictionary.
    Output the words in sorted order.
    Output the words in lower case.
    Avoid following dead-end paths, thus allowing Boggle to run quickly on large boards.
    Your implementation needs to run with large boards and large dictionaries in a reasonable amount of time. 

Command Line

The program is run with the names of the dictionary, board, and output files given on the command-line. For example the program might be run like this:

lab3 dictionary.txt board.txt output.txt


