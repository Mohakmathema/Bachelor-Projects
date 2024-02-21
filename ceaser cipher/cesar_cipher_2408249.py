def welcome(): #creating function named welcome. this prints the below welcome message to the user.
    print("Welcome to the Caesar Cipher\nThis program encrypts and decrypts text with the Caesar Cipher.")
welcome() #here we call the function. it will run the code inside this function.


def encrypt(msg, key): # another function with name encrypt to encrypt the text.
    '''
    two parameters are used:
    :param msg: for message
    :param key: for key/shift
    :return: we use this to return value to the caller by exiting the function
    '''
    encrypted_msg = '' #declaring variable and keeping value null to later put value into it.
    for char in msg: #for loop used in msg string, iterated through every character in the parameter msg.
        if char.isalpha():#check if the character is an alphabet or not.

            shift = ord('a') if char.islower() else ord('A')
            encrypted_char = chr((ord(char) - shift + key) % 26 + shift)
            encrypted_msg += encrypted_char
        else:
            encrypted_msg += char
    return encrypted_msg
# return sends back the stored value back to the caller.
#return can also be used to exit a function.

def decrypt(encrypted_msg, key):
    '''

    :param encrypted_msg: parameter to input encrypted_message for decryption
    :param key: is value by which the encryption/decryption happens
    :return: returning the encrypt function with arguments encrypted_msg and -key
    '''
    return encrypt(encrypted_msg, -key)


def process_file(filename, mode, key):

    '''

    :param filename: parameter to input filename for encryption and decryption
    :param mode: parameter to input mode for encryption and decryption
    :param key: parameter to input key for encryption and decryption
    :return: message by exting the function and calling it
    '''
    messages = [] #empty list
    if is_file(filename): #function is_file used as if statement
        with open(filename, 'r') as file: #opening file as read here with 'r'
            for line in file:
                line = line.strip() # the .strip() method is used here to remove any leading and trailing whitespace from the line
                if mode == 'e':
                    encrypted_msg = encrypt(line, key)
                    messages.append(encrypted_msg)
                elif mode == 'd':
                    decrypted_msg = decrypt(line, key)
                    messages.append(decrypted_msg)
    else:
        print(f"File '{filename}' not found.")
    return messages


def is_file(filename): #function defined as is_file also used as condition in function process_file

    '''

    :param filename: parameter for the name of the file for encryption and decryption
    :return: either true or false using the try: except: exception handlers to check
             code in try is correct if not them the except block of code is triggered.
    '''
    try:
        with open(filename, 'r'):
            pass
        return True
    except FileNotFoundError:
        return False


def enter_messages(messages): #write_message function is created to write the encrypted/decrypted message into a file

    '''

    :param messages: this is the message to encrypt

    '''
    with open('results.txt', 'w') as file:
        for msg in messages:
            file.write(msg + '\n')


def message_or_file(): # new function created
    while True: #using while loop that is always true/always runs(infinite loop)
        '''
        here break must be used to stop the loop from running infinitely.
        '''
        mode = input("Would you like to encrypt (e) or decrypt (d): ").lower()
        if mode not in ['e', 'd']: # we are checking if the value of mode is not present in the list ['e','d'].
            print("Invalid Mode. Please enter 'e' or 'd'.")
        else:
            break # to prevent from infinite loop

    while True:
        method = input("Would you like to read from a file (f) or the console (c)? ").lower()
        if method == 'f': # condition to choose encrypt/decrypt from file or console
            filename = input("Enter a filename: ")
            if is_file(filename):
                break
            else:
                print("Invalid Filename")
        elif method == 'c': # condition to choose encrypt/decrypt from file or console
            filename = None
            break
        else:
            print("Invalid input. Please enter 'f' or 'c'.")

    if method == 'c':
        message = input("What message would you like to process: ")
        return mode, message.upper(), None
    else:
        return mode, None, filename


def main(): #this is enrty point to the python script. it has main logic and control flow of the program

    while True:  #taking user input for mode, message, and filename
        mode, message, filename = message_or_file()

        if filename:
            #asking the user for shift number, handling invalid inputs with a try-except block
            while True:
                try:
                    key = int(input("What is the shift number: ")) #key has use of shift for files.
                    break
                except ValueError:
                    print("Invalid input. Please enter a valid integer for the shift number.")
            #we take messages from the file, write results to 'results.txt' then tell the user.
            messages = process_file(filename, mode, key)
            enter_messages(messages)
            print(f"Messages {'encrypted' if mode == 'e' else 'decrypted'} and written to 'results.txt'")
        else:  #run a single message from the console and print the result in console
            while True:
                try:
                    key = int(input("What is the shift number: "))
                    break
                except ValueError:
                    print("Invalid input. Please enter a valid integer for the shift number.")
            if mode == 'e':
                encrypted_message = encrypt(message, key)
                print("Encrypted message:", encrypted_message)
            elif mode == 'd':
                decrypted_message = decrypt(message, key)
                print("Decrypted message:", decrypted_message)

        while True:
            # Ask the user if they want to run the program again
            run_again = input("Do you want to run the program again? (y/n): ").lower()
            if run_again == 'y' or run_again == 'n':
                break
            else:
                print("Invalid input. Please enter 'yes' or 'no'.")
        if run_again != 'y': # if run_again is not y then the code prints the following:
            print("Thanks for using the program.")
            break #then stops the code from going any further

main()
