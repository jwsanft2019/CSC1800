# Python code created by Jake Sanft
import collections

# create three arrays of numbers
b = ""
a = ""
calledNumbers = ""

# scan in first pattern array and empty line that follows
for i in range(0, 6):
    a += input()
    a += " "

# scan in the second array of called numbers
calledNumbers += input()

# scan in the bingo card array to the EOF
for i in range(0, 6):
    b += input()
    b += " "


# split and create the pattern, calledNumbers, and card arrays of int
pattern = a.split()
pattern = list(map(int, pattern))
card = b.split()
calledNumbers = calledNumbers.split()
calledNumbers = list(map(int, calledNumbers))
calledNumbers.append(00)
card = list(map(int, card))
# print lines for testing purposes
# print(pattern)
# print(calledNumbers)
# print(card)


size = len(calledNumbers)

# check if bingo card is checking for non-crazy pattern
if pattern.count(1) > 0:
    for i in range(0, 25):
        for j in range(0, size):
            if calledNumbers[j] == card[i]:
                card[i] = 1
            else:
                pass

    for i in range(0, 25):
        if card[i] != 1:
            card[i] = 0

    flag = False
    for i in range(0, 25):
        if card[i] != 1 and pattern[i] == 1:
            flag = False
            break
        else:
            flag = True

    # print(card)
    if flag:
        print("Valid Bingo")
    else:
        print("NO Bingo")

# check if bingo card should be checked for 'crazy' pattern
elif pattern.count(4) > 0:
    for i in range(0, 25):
        for j in range(0, size):
            if calledNumbers[j] == card[i]:
                card[i] = 4
            else:
                pass

    for i in range(0, 25):
        if card[i] != 4:
            card[i] = 0

    flag = False
    for i in range(0, 4):
        for j in range(0, 25):
            if card[j] != 4 and pattern[j] == 4:
                flag = False
                break
            else:
                flag = True
        if not flag:
            card = collections.deque(card)
            card.rotate(4)
            card = (list(card))
            # print(card)
            # print(pattern)
        else:
            pass

    if flag:
        print("Valid Bingo")
    else:
        print("NO Bingo")

else:
    print("No Bingo")
