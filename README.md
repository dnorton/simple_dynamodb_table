This project is just my attempt to learn Amazon DynamoDB and get familar
with the AWS Java SDK.

A lot of the code examples are modified from:

<https://github.com/awslabs/aws-dynamodb-examples>


Lots of documentation on the API can be found at the
[AWS DynamoDB Java](http://docs.aws.amazon.com/amazondynamodb/latest/gettingstartedguide/GettingStarted.Java.html) page.

To use a local DynamoDB instance, download it from [here](http://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Tools.DynamoDBLocal.html).

Run:
```bash
java -Djava.library.path=~/path_to_jar/DynamoDBLocal_lib -jar ~/path_to_jar/DynamoDBLocal.jar
```