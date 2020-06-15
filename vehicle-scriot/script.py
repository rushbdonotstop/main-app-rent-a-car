import pika
import base64


# send a single message to the queue
def sendFtnCoordinates():
    message = '45.2461 N, 19.8517 E'
    message_bytes = message.encode('ascii')
    channel.basic_publish(exchange='spring-boot-exchange',
                          routing_key='foo.bar.baz',
                          body=message_bytes)

def openConnection():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    # same as in main app
    channel.queue_declare(queue='spring-boot')
    return connection, channel

def closeConnection(connection):
    connection.close()

connection, channel = openConnection()
sendFtnCoordinates()
closeConnection(connection)
