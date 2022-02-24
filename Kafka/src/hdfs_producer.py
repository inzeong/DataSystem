import csv
from kafka import KafkaProducer
from json import dumps

bootstrap_servers = ["localhost:9092"]

producer = KafkaProducer(
    bootstrap_servers=bootstrap_servers,
    value_serializer=lambda k: dumps(k).encode("utf-8"),
)

with open("load_data.csv", newline="") as csvfile:
    reader = csv.DictReader(csvfile, delimiter=",")
    my_list = list(reader)
    for row in my_list:
        producer.send("house", row)
