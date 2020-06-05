FROM dante2012/tomcat:7.0.100-centos-oraclejdk

LABEL MAINTAINER="dante <dantefreedom@gmail.com>"

##ENV CATALINA_OPTS="-Xms512m -Xmx1024m -Xmn128m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -XX:+PrintGC"
ENV CATALINA_OPTS="-XX:MinRAMPercentage=75.0 -XX:MaxRAMPercentage=75.0 -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m"

COPY target/s2i-tomcat.war webapps/ROOT.war

RUN set -eux; \
    groupadd -r spirit --gid=1000; \
    useradd -r -g spirit --uid=1000 spirit; \
    mkdir -p /home/ap/logs; \
##    chmod -R o+rx .; \
	chown -R spirit:spirit /home/ap/logs; \
    chown -R spirit:spirit .; \
    chmod 777 logs temp work; \
	unzip -oq webapps/ROOT.war -d webapps/ROOT; \
	rm -rf webapps/ROOT.war

USER spirit

EXPOSE 8080

VOLUME ["/home/ap/logs"]

COPY docker-entrypoint.sh /

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["catalina.sh", "run"]