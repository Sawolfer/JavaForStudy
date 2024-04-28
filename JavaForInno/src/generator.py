import random
states = ["a", "b", "c", "v"]
alphabet = ["to_a", "to_b", "bebe", "rer", "rar", "ror", "raror"]
str=''
file = open("input.txt", "w")

count = 10

file.write("type=[deterministic]\n")
file.write("states=[")
for i in range(len(states)):
    file.write(states[i])
    if i != len(states)-1:
        file.write(",")
file.write( "]\n")

file.write("alphabet=[")
for i in range(len(alphabet)):
    file.write(alphabet[i])
    if i != len(alphabet)-1:
        file.write(",")
file.write("]\n")

initial = states[random.randint(0, len(states)-1)]

file.write("initial=[" + initial + "]\n")
file.write("accepting=[")
for i in range(2):
    file.write(states[random.randint(0, len(states)-1)])
    file.write(",")
file.write(states[random.randint(0, len(states)-1)] + "]\n")

trans = ''
file.write("transitions=[")
str = initial + ">" + alphabet[random.randint(0, len(alphabet)-1)] + ">" + states[random.randint(0, len(states)-1)] + ","
# for i in range(count):
#     str = states[random.randint(0, len(states)-1)] + ">" + alphabet[random.randint(0, len(alphabet)-1)] 
#     if str not in trans :
#         str +=  ">" + states[random.randint(0, len(states)-1)]
#     if str not in trans:
#         trans += str + ","
#     else :
#         continue


for i in range(count):
    for j in range(len(alphabet)):
        str = states[random.randint(0, len(states)-1)] + ">" + alphabet[random.randint(0, len(alphabet)-1)] 
        if str not in trans :
            str +=  ">" + states[random.randint(0, len(states)-1)]
        if str not in trans :
            trans += str + ","



trans = trans[:-1]
file.write(trans + "]\n")
file.close()