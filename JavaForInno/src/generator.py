import random
states = ["off", "on"]
alphabet = ["turn_on", "turn_off"]
str=''
file = open("input.txt", "w")

count = 5

file.write("type=[deterministic]\n")
file.write("states=[")
for i in range(random.randint(1, len(states))):
    for j in range(random.randint(1, len(states))):
        file.write(states[random.randint(0, len(states)-1)])
        file.write(",")
file.write(states[random.randint(0, len(states)-1)] + "]\n")

file.write("alphabet=[")
for i in range(random.randint(1, len(alphabet))):
    for j in range(random.randint(1, len(alphabet))):
        file.write(alphabet[random.randint(0, len(alphabet)-1)])
        file.write(",")
file.write(alphabet[random.randint(0, len(alphabet)-1)] + "]\n")

file.write("initial=[" + states[random.randint(0, len(states)-1)] + "]\n")
file.write("accepting=[")
for i in range(random.randint(1, len(states))):
    for j in range(random.randint(1, len(states))):
        file.write(states[random.randint(0, len(states)-1)])
        file.write(",")
file.write(states[random.randint(0, len(states)-1)] + "]\n")

file.write("transitions=[")
for i in range(random.randint(1, count)):
    for j in range(random.randint(1, count)):
        file.write(states[random.randint(0, len(states)-1)])
        file.write(">")
        file.write(alphabet[random.randint(0, len(alphabet)-1)])
        file.write(">")
        file.write(states[random.randint(0, len(states)-1)])
        file.write(",")
file.write(states[random.randint(0, len(states)-1)])
file.write(">")
file.write(alphabet[random.randint(0, len(alphabet)-1)])
file.write(">")
file.write(states[random.randint(0, len(states)-1)])
file.write("]\n")
file.close()