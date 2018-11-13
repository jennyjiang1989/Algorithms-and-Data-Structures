from google.cloud import pubsub_v1
from google.cloud import kms_v1
from flask import request

project_id ='azuqua-pets'
location = 'global'

#This function validates whether the caller is allowed to call the google cloud function
def validate(project_id, location, keyring, key):
    client = kms_v1.KeyManagementServiceClient()
    parent = client.key_ring_path(project_id, location, keyring)
    #list the resource IDs of all keys inside a keyring
    #e.g.projects/azuqua-pets/locations/global/keyRings/azuquaPlatformPreSharedKey/cryptoKeys/keyValue
    allKeys = client.list_crypto_keys(parent)
    #the list of actual keys e.g.keyValue
    KMSkeys=[]
    #if the key provided by customer doesn't exist, return 403 forbidden
    for eachKey in allKeys:
        KMSkeys.append(eachKey.name.split('/')[-1])
    if not key in KMSkeys:
        return ('',403)
    else:
        return 'ok'

#This function publishes a message to a topic
def publishMessage(project_id, topic_name, request_json):
    publisher = pubsub_v1.PublisherClient()
    topic_path = publisher.topic_path(project_id, topic_name)
    data = str(request_json)
    #Data to be published must be a bytestring
    data = data.encode('utf-8')
    publisher.publish(topic_path, data=data)

def response(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/0.12/api/#flask.Flask.make_response>`.
    """
    request_json = request.get_json()
    connectionName = request_json['connectionName']
    responseTopic = str(connectionName) + str('Response')
    preSharedKeyValue = request.headers.get('secret')
    keyring = connectionName
    
    if(validate(project_id, location, keyring, preSharedKeyValue)=='ok'):
        #publish the response to response topic
        publishMessage(project_id,responseTopic,request_json)
    else:
        return ('',403)


