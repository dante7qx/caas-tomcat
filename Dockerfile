FROM tomcat:8.5
LABEL MAINTAINER "dante <ch.sun@haihangyun.com>"

ENV TZ Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone \
	&& rm -rf webapps/*

ADD s2i-tomcat.war webapps/