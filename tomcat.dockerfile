FROM dante2012/tomcat:8.5-jre8-221

LABEL MAINTAINER="dante <dantefreedom@gmail.com>"

WORKDIR /usr/local/tomcat

ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m"
	
COPY target/s2i-tomcat.war webapps/ROOT.war

RUN unzip -oq webapps/ROOT.war -d webapps/ROOT && rm -rf webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]