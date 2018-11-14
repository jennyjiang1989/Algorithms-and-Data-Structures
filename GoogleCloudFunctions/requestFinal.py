from google.cloud import pubsub_v1
from google.cloud import kms_v1
from flask import request
from flask import Response
import json

project_id ='azuqua-pets'
location = 'global'
platformKeyring = "azuquaPlatformPreSharedKey"

#This function validates whether the caller is allowed to call the google cloud function
def validate(project_id, location, keyring, key):
    client = kms_v1.KeyManagementServiceClient()
    parent = client.key_ring_path(project_id, location, keyring)
    #list the resource IDs of all keys inside a keyring
    #e.g.projects/azuqua-pets/locations/global/keyRings/azuquaPlatformPreSharedKey/cryptoKeys/keyValue
    allKeys = client.list_crypto_keys(parent)
    print(allKeys)
    #the list of actual keys e.g.keyValue
    KMSkeys=[]
    for eachKey in allKeys:
        KMSkeys.append(eachKey.name.split('/')[-1])
    #if the key provided by customer doesn't exist, return Unauthorized
    if not key in KMSkeys:
        return 'Unauthorized'
    else:
        return 'ok'

#This function creates a pull subscription to a topic
def createPullSubscription(project_id, topic_name, subscription_name):
    subscriber = pubsub_v1.SubscriberClient()
    topic_path = subscriber.topic_path(project_id, topic_name)
    subscription_path = subscriber.subscription_path(
        project_id, subscription_name)
    subscription = subscriber.create_subscription(subscription_path, topic_path)

    print('Subscription created: {}'.format(subscription))

#This function publishes a message to a topic
def publishMessage(project_id, topic_name, request_json):
    publisher = pubsub_v1.PublisherClient()
    topic_path = publisher.topic_path(project_id, topic_name)
    data = str(request_json)
    #Data to be published must be a bytestring
    data = data.encode('utf-8')
    publisher.publish(topic_path, data=data)
    
    print('Request message published')

#This function synchronously pulls one message. 
#Note it will pull again if nothing returned from the first pull. 
#The designer card will time out after 1 minute.
def pullResponse(project_id, subscription_name, requestGuid):
    num_messages = 1000
    subscriber = pubsub_v1.SubscriberClient()
    subscription_path = subscriber.subscription_path(
            project_id, subscription_name)
    for i in range(3):
        #Request will return after 20 seconds
        response = subscriber.pull(subscription_path, max_messages=num_messages, return_immediately=False, timeout=20)
        messages = response.received_messages
        if len(messages)!=0:
            for item in messages:
                ack_id=item.ack_id
                #this is a byte string
                bytePayload = item.message.data
                #this is a string
                stringPayload = bytePayload.decode('ascii')
                print(stringPayload)
                #replace ' with "
                doubleQuotePayload=stringPayload.replace("'","\"")
                #this is a json
                jsonPayload = json.loads(doubleQuotePayload)
                if(jsonPayload["requestGuid"]==requestGuid):
                    subscriber.acknowledge(subscription_path, [ack_id])
                    return stringPayload
                else:
                    continue
        else:
            continue
    return "Timed Out, no response returned"
            
def request(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/0.12/api/#flask.Flask.make_response>`.
    """
    request_json = request.get_json()
    requestGuid = request_json['requestGuid']
    connectionName = request_json['connectionName']
    requestTopic = str(connectionName) + str('Request')
    responseTopic = str(connectionName) + str('Response')
    platformPreSharedKeyValue = request.headers.get('platformSecret')
    customerKeyring = connectionName
    customerPreSharedKeyValue = request.headers.get('customerSecret')
    #subscription_name = connectionName +str(requestGuid)
    subscription_name = "connectionNameSubscriber"
    
    if(validate(project_id, location, platformKeyring, platformPreSharedKeyValue)=='ok' and validate(project_id, location, customerKeyring, customerPreSharedKeyValue)=='ok'):
        publishMessage(project_id,requestTopic,request_json)
        #createPullSubscription(project_id,responseTopic, subscription_name)
        return pullResponse(project_id,subscription_name,requestGuid)
    elif(validate(project_id, location, platformKeyring, platformPreSharedKeyValue)=='ok'):
        return Response("Invalid customer key",status=401)
    else:
        return Response("Invalid platform key",status=403)
