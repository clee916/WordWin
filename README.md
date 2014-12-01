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

+ Load: 	load multiple gameboards.
+ Print: 	print loaded gameboards on screen.
+ Find: 	find all playable words from a loaded gameboards, sorted by length + vertical reach. It can also display the word on screen, and search for words containing a specific position.
+ Win:	Shows all two-word combinations of a loaded gameboard that will allow you to win.

Code is messy, will comment/clean later on + improve UI
