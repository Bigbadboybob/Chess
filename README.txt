=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1.2-D Arrays: I used the 2d array to model the chess board with each int storing the type of the piece on that space
  and 0 corresponding to an empty space. This was the most intuitive option as a chess board is basically and 8 by
  8 array.

  2.Collections: I used LinkedLists to store three different important lists of moves. The first list was the selection list
  which stored the possible moves of a piece that a user clicked on. This allowed a user to click on a piece and
  have the all spaces with possible moves be highlighted so that they could select one of these moves. Next I
  used a LinkedList to store all previous moves. This was done so that I could implement en passant and castling
  but also so that I could add an undo feature. Finally I used another LinkedList to store redo moves. A
  LinkedList was the best option here because I needed to store singular data elements and needed access to
  the head of the list. I also needed to be able to continuously add more and more elements to the list so an
  array would not work for this.
  
  I also briefly used a setMap in the PieceType enum so that I was able to get the PieceType from the corrosponding
  int value.

  3.File I/O: I used File I/O to be able to save and load games with external txt files. I created methods to make strings
  for each field in the Chess class so that I could store these fields in different lines of the txt file. Then I created
  a load function which would read the file line by line and parse the string representations of the fields in order
  to convert them back to their respective data types. File I/O was needed here because I needed to save the game
  state in an external file so that it would remain saved even after the game is closed.

  4. Testable Component: I used JUnit to test the undo and redo functions. I created a method that would clone the game model
  state so that I could test in various ways that performing a move and undoing it would return the game model to the same
  state that it was in before performing the move. I did the same with the redo function. Two edge cases I tested were performing
  undo or redo when there were moves left to undo or redo.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Chess.java: This is model for the game so it stores the game state and provides various methods for the
  game. The two most important methods are getMoves which returns a list of all possible moves for a
  particular space and executeMoves which just carries out the move updating the game state accordingly.
  
  Game.java: This class is mostly the same as the provided Game.java class, it mostly just instantiates
  GameBoard and adds the buttons at the top.
  
  GameBoard.java This is a modified version of the provided Gameboard.java class. It creates the mouseListener
  and provides a method for painting the chess game.
  
  Move.java This class stores the information on each move through class fields and provides toString and fromString
  methods which are important for saving and loading the game.
  
  Pieces.java This class really just provides a method for drawing the chess pieces.
  
  PieceType.java This enum stores all different types of pieces including EMPTY which is just an empty space.
  
  SaveLoad.java This class provides the save and load methods
  


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  One stumbling block was implementing en passant and castling. For this project I decided to break things
  up into steps and in this, I decided to implement "normal" moves first and all features which I can
  use to test this such as the drawing and execution of moves. This way I could ensure these basic moves
  worked properly before adding en passant and castling. This led to some problems because I had to make
  modifications in order to fit this into the Move class and properly deal with these moves. I had to 
  change some things around and break some stuff before I could fix it and overall this led to a lot
  of subtle bugs while before implementing, en passant and castling, I hardly ran into any bugs.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I feel that my Chess class is a big bloated and separating some features into another class
  maybe have kept things more organized. But I'm not sure how I would separate this and a lot
  of the length of this class comes from the getMoves method.
  
  I made sure to encapsulate the Chess.java class with the getters and setters but broke 
  encapsulation a bit with the Chess(Chess c) constructor because of a weird bug where I was 
  getting all elements in my list duplicated when comparing them in my tests. I couldn't figure out
  why and this bug didn't show up anywhere else where I used getMoveHistory or getRedoMoves. When I tried 
  c.moveHistory.equals(c.getMoveHistory) and c.redoMoves.equals(c.getRedoMoves)
  it returned true so I'm not exactly sure of this. Fortunately, I only used this method for my
  JUnit tests so this isn't a big deal.
  



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
