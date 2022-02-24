import json
from kafka import KafkaProducer
from kafka.errors import KafkaError


with open("load.json", "r", encoding="utf-8") as f:
    content = f.read()
    json_data = json.loads(content)

topic_name = "house"
bootstrap_servers = ["localhost:9092"]

producer = KafkaProducer(
    bootstrap_servers=bootstrap_servers,
    max_request_size=1073741824,
    buffer_memory=1073741824,
)

print("[Start] Producer 메세지 전송 시작")

for x in json_data:
    byte_value = json.dumps(x).encode("utf-8")

    future = producer.send(topic_name, value=byte_value)

    try:
        record_metadata = future.get()

    except KafkaError as e:
        print("error")
