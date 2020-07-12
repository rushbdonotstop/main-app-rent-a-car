import json
import random

import googlemaps
import pika
import requests
from twisted.internet import task, reactor

from generateCoords import get_points_along_path

global token
timeout = 5.0  # 5 seconds

# send request for jwt token to mainapp
def sendTokenRequest():
    headers = {'Content-Type': 'application/json'}
    urlMain = 'http://localhost:8081/vehicle/token'
    urlAgent = 'http://localhost:8080/token'
    data = '''{
	            "id":1
                }
            '''
    response = requests.post(urlMain, data=data, headers=headers)
    global token
    response_data = json.loads(response.text)
    print(response_data)
    token = response_data["token"]


# send a single message to the queue
def sendCoordinates():
    global token
    global second_counter

    message = '''{
                    \"headers\":  {
                            \"Authorization\":\"''' + token + '''\"
                            },
                    \"body\" : ''' + '\"'''+ str(dict[second_counter][0])+''', '''+ str(dict[second_counter][1]) +'''\"''' + '''
                }'''
    message_bytes = message.encode('ascii')
    print(message_bytes.decode('ascii'))
    channel.basic_publish(exchange='spring-boot-exchange',
                          routing_key='foo.bar.baz',
                          # properties=pika.BasicProperties(
                          #     content_type='application/json'
                          # ),
                          body=message_bytes)
    second_counter+=15
    pass


def openConnection():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost:5672'))
    channel = connection.channel()
    # same as in main app
    channel.queue_declare(queue='spring-boot-coords')
    return connection, channel


def closeConnection(connection):
    connection.close()


def getRandomCities():
    cities = ['Eiffel Tower, Avenue Anatole France, Paris, France', 'Leaning Tower of Pisa, Pisa, Province of Pisa, Italy', 'Piața Arcul de Triumf, București, Romania', 'Đavolja Varoš, Mehane',
              'Berlin Wall Memorial, Bernauer Straße, Berlin, Germany']
    random_city_index_start = random.randrange(5)
    random_city_index_end = random.randrange(5)
    while random_city_index_end == random_city_index_start:
        random_city_index_end = random.randrange(5)
    print(cities[random_city_index_start], cities[random_city_index_end])
    return cities[random_city_index_start], cities[random_city_index_end]

def getCoords(city1, city2):
    dict = get_points_along_path("AIzaSyBcBUQxfS6JldNG0Ltoju5YxE_0-CKJsu4", city1,
                                 city2, departure_time=None, period=5)
    return dict


sendTokenRequest()
connection, channel = openConnection()

randomCities = getRandomCities()
dict = getCoords(randomCities[0], randomCities[1])
print(dict)

second_counter = 0
l = task.LoopingCall(sendCoordinates)
l.start(timeout) # call every 5 seconds

reactor.run()
closeConnection(connection)



