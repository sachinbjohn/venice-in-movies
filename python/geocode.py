import csv
from geolocation.google_maps import GoogleMaps

data_dir="/home/sachin/Data/EPFL/Sem 2/DH/newdata/"
google_maps = GoogleMaps(api_key=YOUR_KEY) 

with open(data_dir+"locations.csv","r") as fin:
 with open(data_dir+"coordinates.csv","w") as fout:
   reader = csv.reader(fin)
   writer=csv.writer(fout)
   for line in reader:
     location = google_maps.search(location=line[1]).first() # sends search to Google Maps.
     line.append(location.lat)
     line.append(location.lng)
     writer.writerow(line)
