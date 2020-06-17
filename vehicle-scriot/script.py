import json

import pika
import requests
from twisted.internet import task, reactor

global token
timeout = 15.0 # 15 seconds

# send request for jwt token to mainapp
def sendTokenRequest():
    headers = {'Content-type': 'application/json'}
    url = 'http://localhost:8081/vehicle/token'
    data = '''{
	            "id":1
                }
            '''
    response = requests.post(url, data=data, headers=headers)
    global token
    response_data = json.loads(response.text)
    print(response_data)
    token = response_data["token"]


# send a single message to the queue
def sendCoordinates():
    global token
    message = '''{
                    \"headers\":  {
                            \"Authorization\":\" ''' + token + '''\"
                            },
                    \"body\" : ''' + '\"45.2461 N, 19.8517 E\"''' + '''
                }'''
    message_bytes = message.encode('ascii')
    print(message_bytes.decode('ascii'))
    channel.basic_publish(exchange='spring-boot-exchange',
                          routing_key='foo.bar.baz',
                          # properties=pika.BasicProperties(
                          #     content_type='application/json'
                          # ),
                          body=message_bytes)
    pass


def openConnection():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    # same as in main app
    channel.queue_declare(queue='spring-boot')
    return connection, channel


def closeConnection(connection):
    connection.close()


sendTokenRequest()
connection, channel = openConnection()

l = task.LoopingCall(sendCoordinates)
l.start(timeout) # call every 15 seconds

reactor.run()
closeConnection(connection)
