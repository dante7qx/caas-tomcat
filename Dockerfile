FROM tomcat:jre8-user-spirit

LABEL MAINTAINER="dante <dantefreedom@gmail.com>"

ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m"
	
ADD target/s2i-tomcat.war ./webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]