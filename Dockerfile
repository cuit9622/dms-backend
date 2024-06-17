FROM eclipse-temurin:17-jre-ubi9-minimal
WORKDIR /root
COPY server.jar /root
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m"
CMD java ${JAVA_OPTS} -jar ./server.jar
