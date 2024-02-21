import random
import os
import json

def draw_board(board):

    '''

    :param board: parameter named board is set to create the board where the game is played
    :return: displaying the board to the player/user
    '''

    for row in board: #iterating over the board given in the play_games.py file to create a board using symbols of a computer
        print(" "+ " | ".join(row)) #we use in-built method .join() to join the rows together.
       #.join() => in-built method in python - joins iterable/items after every item
        print("---+---+---")
        #this print function is iterating over board so it's printing multiple times

def welcome(board):

    '''

    :param board: taking board as the parameter again but this times it first welcomes the user with te given message
    here we also call the function draw_board to display the drawn board to the player/user.
    :return: displaying the welcome message
    '''

    print("Welcome to Noughts and Crosses or Tic-Tac-Toe! \nThe board is given below:")
    draw_board(board) #calling draw_board inside welcome to print it during welcome message
    #board is local variable
def initialise_board(board): #preparing the game board to be played

    '''

    here using the concept of nested loop
    i first executes once while the j executes three times during 1 execution of i
    eg: when i is 0 the j executes three times as 0, 1 and 2 and goes on till i = 2
    '''
    #if the range of row and column is set different like 2 only 2 of the row/column are set to empty
    for i in range(3): # iterate through each row of board
        for j in range(3): # iterate through each column of board
            board[i][j] = ' ' # to set the cells to empty
    return board
    #returns modified board
def get_player_move(board):

    '''
    using while true loop(infinite loop: needs break to stop the loops execution)
    using try and except(exception handling) to handle any errors/exceptions
    currently used exceptions are ValueError exception.
    :param board:
    :return:
    '''

    while True: # no break used. return immediately exits the function with values specified
        try:
            move = int(input("Choose a Square and Enter the Number Here: "))
            row, col = (move - 1) // 3, (move - 1) % 3 #first part is for row and second is for column
            #the above is formula to calculate the position for X/O input in regard to row/column of board and 1-9 input of the user
            if 1 <= move <= 9 and board[row][col] == ' ':
            #checking if the input position is in valid range and if the position is empty/vacant
                return row, col #calculate row and column of the move and returns these values to be called
            else:
                print("Invalid move. Please try again.")
        except ValueError:
            print("Please enter a number between 1 and 9.")

def choose_computer_move(board): #selects computers moves

    '''
    this function is used to choose the computer move after the player chooses their move
    :param board:
    :return:
    in this the computer move is chosen randomly and placed in a random spot of the board using rows and columns
    '''
    #all possible moves the computer can do

                        #list comprehension in 3*3 2D board
                    #syntax: [expression for item in iterable if condition]


    #creates list of tuples which represents empty cell on board
    possible_moves = [(r, c) for r in range(3) for c in range(3) if board[r][c] == ' ']
    #the above checks for each cell, if the cell is empty. yes then added to possible_moves

    #tuple of row and column chosen by computer is returned
    return random.choice(possible_moves) #randomly selects a move
    #random.choice() function part of python's random module

def check_for_win(board, mark):

    '''
    this function is used to check if the user or the computer is winning or not.
    :param board: this parameter is used to place the X or O in the board
    :param mark: this parameter is used to establish the mark of the player and the computer in the board
    :return:
    '''

                    #tuples can also be used to nest the tuples
    win_conditions = [ #this is a list which has nested list in it and that has nested a tuple in them and this checks the win condition if the game is over or not
        [(0, 0), (0, 1), (0, 2)], [(1, 0), (1, 1), (1, 2)], [(2, 0), (2, 1), (2, 2)],   #rows
        [(0, 0), (1, 0), (2, 0)], [(0, 1), (1, 1), (2, 1)], [(0, 2), (1, 2), (2, 2)],   #columns
        [(0, 0), (1, 1), (2, 2)], [(2, 0), (1, 1), (0, 2)]                              #diagonals
        ]
    for condition in win_conditions:# now we are iterating over the win_conditions list
        #all() function to check if the covered condition has same mark.
        #True returns if the computer/player won
        if all(board[r][c] == mark for r, c in condition): #nesting a condition inside for loop
            return True
    return False #if loop completes wiithout any winning conditions to be met it returns false(no wins)

def check_for_draw(board): #checking if the game ended in a draw

    '''
    this function is used to check if the game has ended in a draw.
    :param board:
    :return: this returns if the game has ended in a draw.
    '''
    #all() function => returns true if all elements in the iterable is TRUE
    #our case the all() function checks if all cells are not empty
    #this checks if all the rows and columns/cells are not empty it returns draw by irirating over the board
    return all(board[r][c] != ' ' for r in range(3) for c in range(3))
    # generator expression uses () => an iterator that generates items on-the-fly
    # (similar to list comprehension but does not store all the moemory at once)
    #syntax: (expression for item in iterable if condition)

def play_game(board):

    '''
    this function is used to play/execute the game where the player and computer takes turns to win the game of Tic-Tac-Toe
    in this we are calling a function using the parameter board and also calling another function named draw_board()
    :return:
    '''

    board = initialise_board(board)
    draw_board(board)
    while True: #using while true infinite loop which is always true.
        # Player's turn
        row, col = get_player_move(board)
        board[row][col] = 'X'
        draw_board(board)
        if check_for_win(board, 'X'):
            print("Player wins!")
            return 1
        if check_for_draw(board):
            print("It's a draw!")
            return 0

        # Computer's turn
        row, col = choose_computer_move(board)
        board[row][col] = 'O'
        print("Computer's move:")
        draw_board(board)
        if check_for_win(board, 'O'):
            print("Computer wins!")
            return -1
        if check_for_draw(board):
            print("It's a draw!")
            return 0 

def menu():

    '''
    this function is the menu of the code. this is used to navigate between playing the game, saving it, displaying the results and exiting the game
    :return: we return the choice of the player to execute one of the choices
    '''

    print("\n1 - Play the game")
    print("2 - Save your score in the leaderboard")
    print("3 - Load and display the leaderboard")
    print("q - End the program")
    choice = input("Enter your choice: ") #this variable asks the user for a input from the above menu
    return choice

def load_scores(): #uses python's json module to parse the scores in JSON format

    '''
    this function is used to load the scores of the players within the console.
    using try and except to handle any errors during file handling using JSON read and parse
    :return:
    '''

    try:
        with open('leaderboard.txt', 'r') as file:
            # trying to read and parse the JSON data
            data = file.read()
            # If the file is empty we initialize it as an empty dictionary
            if not data:
                return {} # returns an empty dictionary
            return json.loads(data)  #this is used to parse the string(data) from JSON to python dictionary
    except FileNotFoundError:
        # when file doesn't exist this return an empty dictionary
        return {} # returns an empty dictionary
    except json.JSONDecodeError: #error decoding JSON data this displays an exception
        #returns an empty dictionary/ this prevents the code from crashing and stopping unexpectedly
        print("Warning: Leaderboard data is corrupted and will be reset.")
        return {} # returns an empty dictionary

def save_score(score):

    '''
    this function is used to save the scores of the player by name.
    if the user wins we add a point to their name and if they lose we deduct/reduce a point from them.
    :param score:
    :return:
    '''

    name = input("Enter your name: ")
    scores = load_scores() #loading existing scores from the files
    scores[name] = score #updating scores
    with open('leaderboard.txt', 'w') as f:
        json.dump(scores, f, indent = 4)  # using indent for clarity (adding white space before scores)
        #json.dump() is used to update the dictionaries and store it into JSON strings
def display_leaderboard(leaders):

    '''
    this function is used to display the leaderboard of the different players within the console.
    the leaderboard is displayed in a nice tabular format.

    '''

    print("\nLeaderboard:")
    for name, score in leaders.items(): #iterating over the dictionary items and printing them respectively.
        print(f"{name}: {score}")
