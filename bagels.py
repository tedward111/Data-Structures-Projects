#by Teddy Willard
#June 7, 2015
#Allows the user to choose a difficulty level, and an amount of digits.  Then, ."

import random

#Generates a random number string of a user-inputted amount of digits.  The user also gets to decide whether or not 0 is an option.
def number(x, y):
    #Sets possible integers based on whether user wants 0 to be an optional integer or not.
    if y == 0:
        integers = list(range(10))
    elif y == 1:
        integers = list(range(1, 10))
    #Randomly generates digits of number and then combines them.
    random.shuffle(integers)
    num = ''
    for i in range(x):
        num += str(integers[i])
        integers.remove(integers[i])
    return num
        
def picoFermiString(num, guess, x, guesses, z):
    #If the user is out of guesses, they lose.
    if guesses == z:
        return 'You lose!'
    if len(guess) == x:
        success = 0
        fermi = 0
        pico = 0
        for i in range(x):
            #If a digit in the user's guess is equal to the digit in the same spot in the                      actual answer, adds 1 to the fermi count.
            if guess[i] == num[i]:
                fermi += 1
                success += 1
            #If a digit in the user's guess is equal to the digit in the same spot in the                  actual answer, adds 1 to the pico count.
            elif guess[i] in num:
                pico += 1
                success += 0.5
        #If the user guesses correctly, they win.
        if success == x:
            return 'You win!'
        #If a guess has nothing but incorrect values, this returns 'Bagels'
        if success == 0:
            return 'Bagels'
        #Returns Fermis and Picos in that order (Fermis first)
        return 'Fermi ' * fermi + 'Pico ' * pico
    
    #Tells user if their guess was the wrong length.
    else:
        print('Your guess is the wrong length.  Try another guess and be sure to enter exactly ' + str(x) + ' digits.')
        

def main():
    #Asks user to input integer value for digits
    x = int(input('Select an amount of digits for your bagels game, from 3 to 8.'))
    #Asks user to say whether they want 0 to be an allowable number.
    y = int(input('Would you like to play with 0 as an allowable number? Enter 0 for yes and 1 for no.'))
    #Asks user to input integer value for difficulty
    difficulty = int(input('What difficulty level would you like to play with?  Enter 1 for easy, 2 for medium, 3 for hard, and 4 for impossible.'))
    #Calculates amount of turns allowed based on difficulty and amount of digits
    if difficulty == 1:
        z = 15 + x
    elif difficulty == 2:
        z = 10 + x
    elif difficulty == 3:
        z = 6 + x
    elif difficulty == 4:
        z = 3 + x
    #Prints instructions that the user will follow.
    print('I am thinking of a number. It is ' + str(x) + ' digits long.  No numbers repeat.  Your job is to guess the number.  When I say "pico" that means you have named a correct number but it is in the wrong location.  When I say "fermi" that means you have named a correct number and placed it in the correct location.  When I say "bagels" that means all your digits are incorrect.  ' + 'You have ' + str(z) + ' chances to guess correctly.')
    #Sets guess count to 0
    guesses = 0
    #Gets random number that the user will try to guess
    tobeguessed = number(x,y)
    while guesses <= z:
        #Adds 1 to guess count
        guesses += 1
        #Lets user input their guess
        guess = input()
        #enters the guess and returns a Pico Fermi string.
        answer = picoFermiString(tobeguessed, guess, x, guesses, z)
        print(answer)
        #Ends game if user wins or loses
        if answer == 'You win!':
            break
        if answer == 'You lose!':
            break
            
if __name__ == '__main__':
    main()