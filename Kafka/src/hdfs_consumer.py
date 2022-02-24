from kafka import KafkaConsumer
import pydoop.hdfs as hdfs

consumer = KafkaConsumer("house")

hdfs_path = "hdfs://localhost:9000/user/jungin/data/"

for message in consumer:

    values = message.value.decode("utf-8")

    with hdfs.open(hdfs_path, "at") as f:
        f.write(f"{values}\n")
