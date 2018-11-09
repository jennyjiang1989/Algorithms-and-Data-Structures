Create Topic

# Function dependencies, for example:
# package>=version
google-cloud-pubsub==0.38.0


from google.cloud import pubsub_v1
project = 'smooth-concept-218220'
topic_name = 'jenny'

def hello_world(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/0.12/api/#flask.Flask.make_response>`.
    """
    publisher = pubsub_v1.PublisherClient()
    topic_path = publisher.topic_path(project, topic_name)
    topic = publisher.create_topic(topic_path)


Delete Topic

# Function dependencies, for example:
# package>=version
google-cloud-pubsub==0.38.0

from google.cloud import pubsub_v1
import flask

project = 'smooth-concept-218220'

def hello_world(request):
    """Responds to any HTTP request.
    Args:
        request (flask.Request): HTTP request object.
    Returns:
        The response text or any set of values that can be turned into a
        Response object using
        `make_response <http://flask.pocoo.org/docs/0.12/api/#flask.Flask.make_response>`.
    """
    topic_name=request.args.get(topicName)
    publisher = pubsub_v1.PublisherClient()
    topic_path = publisher.topic_path(project, topic_name)
    publisher.delete_topic(topic_path)


Receive Messages

# Function dependencies, for example:
# package>=version
google-cloud-pubsub==0.38.0

from google.cloud import pubsub_v1
import flask


project = 'smooth-concept-218220'
subscription_name = 'azuqua'

NUM_MESSAGES=5
ACK_DEADLINE=60
SLEEP_TIME=10

def hello_world(request):
        subscriber = pubsub_v1.SubscriberClient()
        subscription_path = subscriber.subscription_path(
            project, subscription_name)
        response = subscriber.pull(subscription_path, max_messages=NUM_MESSAGES)
        msgs = [{
            'id':str(msg.ack_id),
            'message':str(msg.message.data)
        } for msg in response.received_messages]
        return flask.jsonify(msgs)

Ack Knowledge

# Function dependencies, for example:
# package>=version
google-cloud-pubsub==0.38.0


from google.cloud import pubsub_v1
import flask

project = 'smooth-concept-218220'
subscription_name = 'azuqua'

def hello_world(request):
        ack_id = request.args.get('ack_id')
        subscriber = pubsub_v1.SubscriberClient()
        subscription_path = subscriber.subscription_path(
            project, subscription_name)
        subscriber.acknowledge(subscription_path, [ack_id])
        return ack_id

Subscribe

# Function dependencies, for example:
# package>=version
google-cloud-pubsub==0.38.0
google-cloud-kms==0.2.0

from google.cloud import pubsub_v1
from google.cloud import kms_v1
from flask import request

project_id ='smooth-concept-218220'
location = 'global'
topic_name = 'connectionNameRequest'
subscription_name = 'pushRequests'

def subscribe(request):
    
    request_json = request.get_json()
    customerKey = request.headers.get("secret")
    keyring = request_json['connectionName']
    endpoint = request_json['callbackEndpoint']
    
    client = kms_v1.KeyManagementServiceClient()
    parent = client.key_ring_path(project_id, location, keyring)
    resp = client.list_crypto_keys(parent)
    KMSkeys=[]
    for key in resp:
        KMSkeys.append(key.name.split('/')[-1])
    if not customerKey in KMSkeys:
        return ('',403)
    else:
        subscriber = pubsub_v1.SubscriberClient()
        topic_path = subscriber.topic_path(project_id, topic_name)
        subscription_path = subscriber.subscription_path(project_id, subscription_name)
        push_config = pubsub_v1.types.PushConfig(push_endpoint=endpoint)
        subscription = subscriber.create_subscription(subscription_path, topic_path, push_config)



