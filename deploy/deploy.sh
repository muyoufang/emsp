docker rm -f emsp
docker build -t emsp .
docker run --name emsp -d
  -e JAVA_OPTS='-Xmx2688M -Xms2688M -Xmn960M -XX:MaxMetaspaceSize=256M -XX:MetaspaceSize=256M' \
  om-jh
docker logs emsp -f
