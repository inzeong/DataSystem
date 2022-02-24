from kafka import KafkaConsumer
import pandas as pd

topic_name = "house"
bootstrap_servers = ["localhost:9092"]

consumer = KafkaConsumer(
    topic_name,
    bootstrap_servers=bootstrap_servers,
    auto_offset_reset="earliest",
    enable_auto_commit=True,
)

print("[Start] Topic : %s 으로 Consumer가 메세지 받는다" % (topic_name))

for message in consumer:
    print(" Value: %s" % (message.value))
