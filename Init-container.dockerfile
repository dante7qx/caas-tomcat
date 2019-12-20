FROM busybox:latest
WORKDIR /app
COPY target/s2i-tomcat.war ROOT.war
RUN unzip -oq ROOT.war -d ./ && rm -rf ROOT.war