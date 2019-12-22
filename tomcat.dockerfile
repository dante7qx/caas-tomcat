FROM dante2012/tomcat:8.5.50-openjdk-1.8.0_40-jre 

LABEL MAINTAINER="dante <dantefreedom@gmail.com>"

ENV JAVA_OPTS="-Xms512m -Xmx512m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m"

RUN set -eux; \
    groupadd -r spirit --gid=1000; \
    useradd -r -g spirit --uid=1000 spirit; 
##    \
##    chmod -R o+rx .; \
##    chown -R spirit:spirit .; \
##    chmod 777 logs temp work
	
COPY target/s2i-tomcat.war webapps/ROOT.war

RUN unzip -oq webapps/ROOT.war -d webapps/ROOT && rm -rf webapps/ROOT.war

## USER spirit

EXPOSE 8080

CMD ["catalina.sh", "run"]