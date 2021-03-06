FROM ubuntu:16.04
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  apt-get update && apt-get install -y software-properties-common && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer \
	maven && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer


# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ENV HZ_HOME /opt/hazelcast
RUN mkdir -p $HZ_HOME
WORKDIR $HZ_HOME
COPY . $HZ_HOME

RUN ls -lah
RUN java -version
RUN mvn --version
RUN mvn -e -DskipTests=true clean install

WORKDIR $HZ_HOME/hazelcast/
RUN ls -lah $HZ_HOME
RUN ls -lah $HZ_HOME/hazelcast
RUN ls -lah target
RUN mv target/hazelcast-3.8-RC1.jar $HZ_HOME

RUN chmod +x $HZ_HOME/server.sh
RUN chmod +x $HZ_HOME/stop.sh
# Start hazelcast standalone server.
CMD ["./server.sh"]
EXPOSE 5701
