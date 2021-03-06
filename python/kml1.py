import csv
import json
from urllib.request import urlopen
from urllib.parse import urlencode
data_dir="/home/sachin/Data/EPFL/Sem 2/DH/newdata/"
lidmid={}
locations={}
imdb="http://www.imdb.com/title/"
print("<?xml version='1.0' encoding='UTF-8'?>")
print("<kml xmlns='http://www.opengis.net/kml/2.2'>")
print("<Document>")
print("<name>Links only</name>")

with open(data_dir+"coordinates.csv","r") as fin:
   reader = csv.reader(fin)
   for line in reader:
     locations[line[0]]=line[1:]
	
with open(data_dir+"precisemovielocations.csv","r") as fin:
   reader = csv.reader(fin)
   for line in reader:
     try:
	
        l=lidmid[line[1]]
        l.append(line[0])
     except KeyError:
        l=[line[0]]
     lidmid[line[1]]=l
address="http://www.omdbapi.com/?"

for l in lidmid.keys():
  print("<Placemark>")
  print("\t<name>"+locations[l][0]+"</name>")
  print("\t<description><![CDATA[")
  for mid in lidmid[l]:
    j=json.loads(urlopen(address+urlencode({'i':mid})).read().decode('utf-8'))
   
    try:
      parent=j['seriesID']
      j2=json.loads(urlopen(address+urlencode({'i':parent})).read().decode('utf-8'))
      name=j2['Title']
    except Exception:
      name=j['Title']  
    print("\t\t<a href="+imdb+mid+">"+name+"("+j['Year']+")</a>")
  print("\t]]></description>")
  print("\t<Point><coordinates>"+locations[l][2]+","+locations[l][1]+",0</coordinates></Point>")
  print("</Placemark>")
print("</Document></kml>")
