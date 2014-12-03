WordWin
====================
"_From WordBase to WordWin, from a game to a win_"

**Author: Cornell Lee**

Generates potential playable words for user in MobileApp: WordBase

##Formatting the board
+ Type the board out in a text file starting from the top left corner.
+ Capitalize the letters that you "possess".
+ Multiples lines / white spaces are all right.
+ Standard WordBase Game has 13 rows/10 columns = 130 characters (not including white space).
+ Text file should be in the same directory as jar-file.

Sample text files has been included
("Andy.txt", "jbbizz.txt", "evil.txt", "board.txt")

##Possible Functions

+ `load`: 	load multiple gameboards.
+ `print`: 	print loaded gameboards on screen.
+ `find`: 	find all playable words from a loaded gameboards, sorted by length + vertical reach, can display the word on screen, and search for words containing a specific/multiple position.
+ `win`:	conducts in depth analysis on the entire board.

####In Depth Analysis
+ `opponent`: Show words / all starting positions of words that opponent can play as a winning move.
+ `user`: Show words / all starting positions of words that user can play as a winning move. Can also show current playable words of user that will lead to a winning move the succeeding turn.

**TODO's**

+ Comment/clean code as its quite messy
+ Improve UI
+ Give user greater control of words seen in In Depth Analysis
