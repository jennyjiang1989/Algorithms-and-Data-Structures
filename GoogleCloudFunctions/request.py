from google.cloud import pubsub_v1
from google.cloud import kms_v1
from flask import request

project_id ='azuqua-pets'
location = 'global'
keyring = "azuquaPlatformPreSharedKey"

#This function validates whether the caller is allowed to call the google cloud function
def validate(project_id, location, keyring, key):
    client = kms_v1.KeyManagementServiceClient()
    parent = client.key_ring_path(project_id, location, keyring)
    #list the resource IDs of all keys inside a keyring
    #e.g.projects/azuqua-pets/locations/global/keyRings/azuquaPlatformPreSharedKey/cryptoKeys/keyValue
    allKeys = client.list_crypto_keys(parent)
    #the list of actual keys e.g.keyValue
    KMSkeys=[]
    for eachKey in allKeys:
        KMSkeys.append(eachKey.name.split('/')[-1])
    #if the key provided by customer doesn't exist, return 403 forbidden
    if not key in KMSkeys:
        return ('',403)
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
def pullResponse(project_id, subscription_name):
    num_messages = 1
    subscriber = pubsub_v1.SubscriberClient()
    subscription_path = subscriber.subscription_path(
            project_id, subscription_name)
    for i in range(2):
        #Request will return after 20 seconds
        response = subscriber.pull(subscription_path, max_messages=num_messages, return_immediately=False, timeout=20)
        if len(response.received_messages)!=0:
            item = response.received_messages[0]
            ack_id = item.ack_id
            #this is a byte string
            payload = item.message.data
            subscriber.acknowledge(subscription_path, [ack_id])
            return payload.decode('ascii') #this is a string
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
    preSharedKeyValue = request.headers.get('secret')
    subscription_name = connectionName +str(requestGuid)
    
    if(validate(project_id, location, keyring, preSharedKeyValue)=='ok'):
        publishMessage(project_id,requestTopic,request_json)
        createPullSubscription(project_id,responseTopic, subscription_name)
        return pullResponse(project_id,subscription_name)
    else:
        return ('',403)
