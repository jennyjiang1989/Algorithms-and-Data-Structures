Save to DB
Trigger: Cloud Pub/Sub

{
  "name": "sample-pubsub",
  "version": "0.0.1",
  "dependencies": {
    "mysql": "^2.16.0"
  }
}


/**
 * Triggered from a message on a Cloud Pub/Sub topic.
 *
 * @param {!Object} event Event payload and metadata.
 * @param {!Function} callback Callback function to signal completion.
 */
const mysql=require('mysql');
//connectionName can be found in the overview part of the created mysql instance
const connectionName='smooth-concept-218220:us-central1:myinstance';
const dbUser='root';
const dbPassword='20171030';
const dbName='mydatabase';

const mysqlConfig = {
  connectionLimit: 1,
  user: dbUser,
  password: dbPassword,
  database: dbName,
  socketPath: `/cloudsql/${connectionName}`
};

exports.helloPubSub = (event, callback) => {
  console.log(JSON.stringify(event));
  const pubsubMessage = event.data;
  let uuid = pubsubMessage.attributes.UUID;
  console.log(uuid);
  let message = Buffer.from(pubsubMessage.data, 'base64').toString();
  console.log(message);
  let mysqlPool=mysql.createPool(mysqlConfig);
  let sql = `insert into entries (uuid, message) values ('${uuid}','${message}')`;
  console.log(sql);
  mysqlPool.query(sql,(error,results)=>{
    if(error){
      console.log(error);
    }else{
      console.log(JSON.stringify(results));
    };
    callback();
  })
};

Query DB

{
  "name": "sample-http",
  "version": "0.0.1",
  "dependencies": {
    "mysql": "^2.16.0"
  }
}

/**
 * Responds to any HTTP request.
 *
 * @param {!express:Request} req HTTP request context.
 * @param {!express:Response} res HTTP response context.
 */
const mysql=require('mysql');
const connectionName='smooth-concept-218220:us-central1:myinstance';
const dbUser='root';
const dbPassword='20171030';
const dbName='mydatabase';

const mysqlConfig = {
  connectionLimit: 1,
  user: dbUser,
  password: dbPassword,
  database: dbName,
  socketPath: `/cloudsql/${connectionName}`
};

exports.helloWorld = (req, res) => {
  const uuid = req.query.uuid;
  const mysqlPool = mysql.createPool(mysqlConfig);

  const sql = `select * from entries where uuid=${uuid}`;
  mysqlPool.query(sql, (error, results) => {
    if (error) {
      console.log(error);
      res.status(500).send(error);
    } else {
      res.status(200).send(JSON.stringify(results));
    }
  });
};

Publish Message

{
  "name": "sample-http",
  "version": "0.0.1",
  "dependencies": {
    "@google-cloud/pubsub": "^0.17.0"
  }
}

// Imports the Google Cloud client library
const PubSub = require('@google-cloud/pubsub');

// Creates a client
const pubsub = new PubSub();

exports.publishMessage = (req, res) => {
  let topicName = req.body.topicName;
  let data = JSON.stringify(req.body.message); 
  const dataBuffer = Buffer.from(data);
  pubsub
  .topic(topicName)
  .publisher()
  .publish(dataBuffer)
  .then(messageId => {
    res.status(200).send(data + '\n' + messageId + '\n');
    console.log(messageId);
  })
  .catch(err => {
    res.status(500).send(err + '\n');
  });
};


Send

{
  "name": "sample-http",
  "version": "0.0.1",
  "dependencies": {
    "@google-cloud/pubsub": "^0.17.0"
  }
}

// Imports the Google Cloud client library
const PubSub = require('@google-cloud/pubsub');

// Creates a client
const pubsub = new PubSub();

exports.send = (req, res) => {
  let topicName = req.body.topicName;
  let uuid=req.body.uuid;
  let data = JSON.stringify(req.body.message); 
  const dataBuffer = Buffer.from(data);
  //UUID is stored as an attribute of the message
  const customAttributes = {
    UUID: uuid
  };
  pubsub
  .topic(topicName)
  .publisher()
  .publish(dataBuffer, customAttributes)
  .then(messageId => {
    res.status(200).send(data + '\n' + messageId + '\n');
    console.log(messageId);
  })
  .catch(err => {
    res.status(500).send(err + '\n');
  });
};

