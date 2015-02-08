# ConnectFour
A Connect Four game in Java with two implementations of getting input from the user.
One through the command line and another using JOptionPane to get input directly
from the user.

There is four different kind of players: Human, Random AI, Bad AI, and Good AI.

The Human player must enter their own input to choose which column to drop
their piece into.

The Random AI chooses a random column to drop their piece into.

The Bad AI always drops their piece into the leftmost least filled column.

The Good AI attempts to choose the best place for their piece to be dropped
in order to win the game. Also blocking the opponent if they will win on
the next turn.
