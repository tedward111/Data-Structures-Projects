#by Teddy Willard
#June 7, 2015
#Generates a three digit number as a string and then has the computer guess it by playing the game "Bagels."

import bagels
import random

#Returns a string of Fermis and Picos, or returns a statement saying that the game is over, or returns 'Bagels'
def picoFermiString(num, guess, x, guesses, z):
    #If the computer is out of guesses, they lose.
    if guesses == z:
        return 'I lose!'
    if len(guess) == x:
        success = 0
        fermi = 0
        pico = 0
        for i in range(x):
            #If a digit in the computer's guess is equal to the digit in the same spot in the                  actual answer, adds 1 to the fermi count.
            if guess[i] == num[i]:
                fermi += 1
                success += 1
            #If a digit in the computer's guess is equal to the digit in the same spot in the                  actual answer, adds 1 to the pico count.
            elif guess[i] in num:
                pico += 1
                success += 0.5
        #If the computer guesses correctly, they win.
        if success == x:
            return 'I win!'
        #If a guess has nothing but incorrect values, this returns 'Bagels'
        if success == 0:
            return 'Bagels'
        #Returns Fermis and Picos in that order (Fermis first)
        return 'Fermi ' * fermi + 'Pico ' * pico

def initialGuesser(tobeguessed, guesses, x, y, z):
    #Allowable digits are 1-9.
    digits = '123456789'
    #Makes it so a = i, b = i + 1, and c = i + 2
    a = digits[guesses - 10]
    b = digits[guesses - 9]
    c = digits[guesses - 8]
    guess = a + b + c
    #prints the computer's guess
    print(guess)
    #Adds 1 to the amount of guesses the computer has used
    guesses += 1
    #enters the guess and returns a Pico Fermi string.
    answer = picoFermiString(tobeguessed, guess, x, guesses, z)
    print(answer)
    #Ends program if computer wins or loses
    if answer == 'I win!':
        quit()
    if answer == 'I lose!':
        quit()

#Same as the initialGuesser function but this one doesn't print anything and does return the Pico Fermi string.
def noPrintInitialGuesser(tobeguessed, guesses, x, y, z):
    digits = '123456789'
    a = digits[int(guesses) - 10]
    b = digits[int(guesses) - 9]
    c = digits[int(guesses) - 8]
    guess = a + b + c
    if guesses == guesses and guesses == 8:
        guess = str(891)
    if guesses == guesses and guesses == 9:
        guess = str(912)
    answer = picoFermiString(tobeguessed, guess, x, guesses, z)
    if answer == 'I win!':
        quit()
    if answer == 'I lose!':
        quit()
    return answer

#Same as the noPrintInitialGuesser function but this one is for the secondaryGuesser.
def noPrintSecondaryGuesser(j, digits, tobeguessed, guesses, x, y, z):
    a = digits[j - 1 - len(digits)]
    b = digits[j - len(digits)]
    c = digits[j + 1 - len(digits)]
    guess = a + b + c
    answer = picoFermiString(tobeguessed, guess, x, guesses, z)
    if answer == 'I win!':
        quit()
    if answer == 'I lose!':
        quit()
    return answer

def secondaryGuesser(tobeguessed, x, y, z):
    #Allowable digits are 1-9.
    digits = '123456789'
    #Allowable digits are 1-9.  This string, unlike the previous one, will not be edited.
    numbers = '123456789'
    for i in range(1, 10):
        #If a guess resulted in 'Bagels,' this makes all the digits that were in that guess into          spaces in the allowable digits string
        if noPrintInitialGuesser(tobeguessed, numbers[i - 1], x, y, z) == 'Bagels':
            digits = digits.replace(digits[i - 1], ' ')
            digits = digits.replace(digits[i - 9], ' ')
            digits = digits.replace(digits[i - 8], ' ')
        #If a guess resulted in 2 correct values, and the guess above or below it resulted in              only 1 correct value, make the appropriate value a space in the allowable digits string.          For example, if 2 numbers in the guess '456' were corrrect but only 1 number in '567'            was correct, the number 7 is turned into a space in the allowable digits string.
        elif noPrintInitialGuesser(tobeguessed, numbers[i - 1], x, y, z) == 'Fermi Fermi ' or noPrintInitialGuesser(tobeguessed, numbers[i - 1], x, y, z) == 'Fermi Pico ' or noPrintInitialGuesser(tobeguessed, numbers[i - 1], x, y, z) == 'Pico Pico ':
            if noPrintInitialGuesser(tobeguessed, numbers[i - 9], x, y, z) == 'Fermi ' or noPrintInitialGuesser(tobeguessed, numbers[i - 9], x, y, z) == 'Pico ':
                digits = digits.replace(digits[i - 7], ' ')
            if noPrintInitialGuesser(tobeguessed, numbers[i - 2], x, y, z) == 'Fermi ' or noPrintInitialGuesser(tobeguessed, numbers[i - 2], x, y, z) == 'Pico ':
                digits = digits.replace(digits[i - 2], ' ')
    #Deletes spaces thereby shortening the allowable digits string.
    digits = digits.replace(' ', '')
    #The next three lines shuffle the digits at random.
    digits = list(digits)
    random.shuffle(digits)
    digits = ''.join(digits)
    #Sets the guess total to 9.
    guesses = 9
    d = len(digits)
    #Goes through the newly shuffled digits string, and reguesses.
    for j in range(1, d + 1):
        guesses += 1
        guess = ''
        a = digits[j - 1 - d]
        b = digits[j - d]
        c = digits[j + 1 - d]
        guess = a + b + c
        print(guess)
        answer = picoFermiString(tobeguessed, guess, x, guesses, z)
        print(answer)
        if answer == 'I win!':
            quit()
        if answer == 'I lose!':
            quit()
        #Goes to tertiary guesser if all three numbers guessed are in the correct answer
        if answer == 'Fermi Pico Pico ' or answer == 'Pico Pico Pico ':
            digits = a + b + c
            tertiaryGuesser(digits, tobeguessed, x, y, z, guesses)
        #This deletes the digits from the allowable digits string of any guesses that resulted in          an answer of 'Bagels'
        if answer == 'Bagels':
            digits = digits.replace(a, '')
            digits = digits.replace(b, '')
            digits = digits.replace(c, '')
    d = len(digits)
    for j in range(1, d + 1):
        #This deletes the digits from the allowable digits string of any guesses that resulted in          an answer of 'Bagels'
        if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Bagels':
            digits = digits.replace(digits[j - d - 1], ' ')
            digits = digits.replace(digits[j - d], ' ')
            digits = digits.replace(digits[j - d - 2], ' ')
        #If a guess resulted in 2 correct values, and the guess previous to or after it                    resulted in only 1 correct value, make the appropriate value a space in the                      allowable digits string.
        elif noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Fermi Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Fermi Pico ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Pico Pico ':
            if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d], x, y, z) == 'Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d], x, y, z) == 'Pico ':
                digits = digits.replace(digits[j - d + 2], ' ')
            if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 2], x, y, z) == 'Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 2], x, y, z) == 'Pico ':
                digits = digits.replace(digits[j - d - 2], ' ')
    #All of this after here is repeated code.
    digits = digits.replace(' ', '')
    digits = list(digits)
    random.shuffle(digits)
    digits = ''.join(digits)
    d = len(digits)
    for j in range(1, d + 1):
        guesses += 1
        guess = ''
        a = digits[j - 1 - d]
        b = digits[j - d]
        c = digits[j + 1 - d]
        guess = a + b + c
        print(guess)
        answer = picoFermiString(tobeguessed, guess, x, guesses, z)
        print(answer)
        if answer == 'I win!':
            quit()
        if answer == 'I lose!':
            quit()
        if answer == 'Fermi Pico Pico ' or answer == 'Pico Pico Pico ':
            digits = a + b + c
            tertiaryGuesser(digits, tobeguessed, x, y, z, guesses)
        if answer == 'Bagels':
            digits = digits.replace(a, '')
            digits = digits.replace(b, '')
            digits = digits.replace(c, '')
    d = len(digits)
    for j in range(1, d + 1):
        if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Bagels':
            digits = digits.replace(digits[j - d - 1], ' ')
            digits = digits.replace(digits[j - d], ' ')
            digits = digits.replace(digits[j - d - 2], ' ')
        elif noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Fermi Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Fermi Pico ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 1], x, y, z) == 'Pico Pico ':
            if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d], x, y, z) == 'Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d], x, y, z) == 'Pico ':
                digits = digits.replace(digits[j - d + 2], ' ')
            if noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 2], x, y, z) == 'Fermi ' or noPrintSecondaryGuesser(j, digits, tobeguessed, numbers[j - d - 2], x, y, z) == 'Pico ':
                digits = digits.replace(digits[j - d - 2], ' ')
    digits = digits.replace(' ', '')

#Randomly guesses once digits have been narrowed down to three total options
def tertiaryGuesser(digits, tobeguessed, x, y, z, guesses):
    #The next three lines shuffle the digits at random.
    digits = list(digits)
    random.shuffle(digits)
    digits = ''.join(digits)
    #The remaining portion of this function guesses all 6 possibile 3 digit strings.
    for j in range(1, 4):
        guess = ''
        a = digits[j - 4]
        b = digits[j - 3]
        c = digits[j - 2]
        guess = a + b + c
        print(guess)
        guesses += 1
        answer = picoFermiString(tobeguessed, guess, x, guesses, z)
        print(answer)
        if answer == 'I win!':
            quit()
        if answer == 'I lose!':
            quit()
    for j in range(4, 7):
        guess = ''
        a = digits[j - 5]
        b = digits[j - 6]
        c = digits[j - 7]
        guess = a + b + c
        print(guess)
        guesses += 1
        answer = picoFermiString(tobeguessed, guess, x, guesses, z)
        print(answer)
        if answer == 'I win!':
            quit()
        if answer == 'I lose!':
            quit()
def main():
    #Sets the digit amount to 3
    x = 3
    #Sets the existence of 0 as a possible value to no
    y = 1
    #Sets the difficulty level to 1.  This, of course, can be changed, but only here, in the          code.
    difficulty = 1
    if difficulty == 1:
        z = 15 + x
    elif difficulty == 2:
        z = 10 + x
    elif difficulty == 3:
        z = 6 + x
    elif difficulty == 4:
        z = 3 + x
    #Prints instructions that the computer will follow.
    print('A random number has been generated. It is ' + str(x) + ' digits long.  No numbers   repeat.  I will now try to guess the number.  "Pico" means I have named a correct number but it is in the wrong location.  "Fermi" means I have named a correct number and placed it in the correct location.  "Bagels" means all my digits are incorrect.  ' + 'I have ' + str(z) + ' chances to guess correctly.')
    #Generates a random number
    tobeguessed = bagels.number(x, y)
    #Prints the number the computer is trying to guess so that the user can see it
    print('I am trying to guess this number:' + tobeguessed)
    for i in range(1, 10):
        #First ten guesses are controlled by the initial guesser.  After that, the secondary              guesser takes over.
        initialGuesser(tobeguessed, i, x, y, z)
    secondaryGuesser(tobeguessed, x, y, z)
            
if __name__ == '__main__':
    main()