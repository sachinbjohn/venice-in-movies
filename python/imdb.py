from urllib.request import urlopen
from urllib.parse import urlencode
from html.parser import HTMLParser
import csv
data_dir="/home/sachin/ramdisk/"
class IDParser(HTMLParser):
  def handle_starttag(self, tag, attrs):
        if tag == 'a':
            for attr in attrs:
                if attr[0] == 'href' and attr[1].startswith('/title/tt'):
                    tt=attr[1].split("/")[2]
                    self.names.add(tt)

  def __init__(self):
     HTMLParser.__init__(self)
     self.names=set()
class locParser(HTMLParser):
  def handle_data(self, data):
    if data.strip().endswith("Venice, Veneto, Italy"):
      
      self.locs.add(data.strip())
  def __init__(self):
     HTMLParser.__init__(self)
     self.locs=set()
    
	
address="http://www.imdb.com/search/title?"
IDs=set()
locations={'Venice, Veneto, Italy':0}
count=1

for start in range(1,750,50):
  html=urlopen(address+urlencode({'locations':'Venice, Veneto, Italy', 'start':start})).read().decode("utf-8")
  idp=IDParser()
  idp.feed(html)
  print (start," ",len(idp.names))	
  IDs |= idp.names
print(len(IDs))	

with open(data_dir+"movielocations.csv","w") as fout:
 with open(data_dir+"precisemovielocations.csv","w") as foutp:
  with open(data_dir+"locations.csv","w") as floc: 
   writer = csv.writer(fout)
   pw = csv.writer(foutp)
   wloc = csv.writer(floc)
   for movie in IDs:
     add2="http://www.imdb.com/title/"+movie+"/locations"
     html=urlopen(add2).read().decode("utf-8")
     locP=locParser()
     locP.feed(html)
     for location in locP.locs:
      try:
          lid=locations[location]
      except KeyError:
          lid=count
          locations[location]=lid
          count=count+1
          wloc.writerow([lid,location]) 
      row=[movie,lid]
 
      if location == "Venice, Veneto, Italy":
        writer.writerow(row)
      else:
        pw.writerow(row)
      print(row)
 
