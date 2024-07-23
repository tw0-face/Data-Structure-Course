#!/usr/bin/python3

#append neighbored neighbours
def AppenNe(l):
    for i in range(len(l)):
        for j in range(len(l)):
            if(any(x in l[j] for x in l[i])):
                l[i].extend(y for y in l[j] if y not in l[i])
                l[i].sort()

#find center of multiple points    
def findCent(l):
  x_cor = [i[0] for i in l]
  y_cor = [i[1] for i in l]
  min_x = (min(x_cor))*2+1
  max_x = (max(x_cor))*2+2
  min_y = (min(y_cor))*2+1
  max_y = (max(y_cor))*2+2
  center = (int((min_y+max_y)/2), int((min_x+max_x)/2))
  return center

#funcion to find players
def findPlayers(photo, team, threshold):
    index = [[x,y] for x, i in enumerate(photo) for y, j in enumerate(i) if j == str(team)]
    #function to get neighbour cells
    neighbors = lambda my_list : [[my_list[0]+a[0], my_list[1]+a[1]] for a in [(0,0), (-1,0), (1,0), (0,-1), (0,1)] if [my_list[0]+a[0], my_list[1]+a[1]] in index]
    res = list(map(neighbors,index))
    for _ in range(len(res)):
        AppenNe(res)
    test = []
    #filter by threshold and remove duplicates
    [test.append(x) for x in res if x not in test and len(x)*4 >= threshold]
    center = list(map(findCent,test))
    center.sort()
    return center

center = []

#taking input
val = input()
(rows, cols) = (int(val.split(",")[0]),int(val.split(",")[1]))
photo = []
for i in range(rows):
  photo.append(input())
team = int(input())
threshold =int(input())

#check for constraints
if 1 <= len(photo) <= 50 or 1 <= threshold <= 10000:
    center = findPlayers(photo, team, threshold)

print(center)