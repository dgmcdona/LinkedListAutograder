# Autolab - autograding docker image (trying bionic ubuntu - May 2019)

FROM ubuntu:18.04
MAINTAINER mtoups / Ted <sysadmin@cs.uno.edu>

RUN DEBIAN_FRONTEND=noninteractive apt-get update --fix-missing
RUN apt-get install -y build-essential python2.7 python3 zip unzip rsync make sudo software-properties-common
# added python2.7 for autograder program, also needs unzip
#RUN add-apt-repository ppa:linuxuprising/java
#RUN sudo apt-get update
# line below makes it so apt doesn't hang waiting for keyboard input
#RUN echo oracle-java11-installer shared/accepted-oracle-license-v1-2 select true | /usr/bin/debconf-set-selections
#RUN apt-get install -y oracle-java11-installer-local
# no more oracle! they suck. Let's use OpenJDK
RUN DEBIAN_FRONTEND=noninteractive apt-get install -y openjdk-11-jdk

# Install autodriver
WORKDIR /home
RUN useradd autolab
RUN useradd autograde
RUN mkdir autolab autograde output
RUN chown autolab:autolab autolab
RUN chown autolab:autolab output
RUN chown autograde:autograde autograde
RUN apt-get install -y git
RUN git clone https://github.com/autolab/Tango.git
WORKDIR Tango/autodriver
RUN make clean && make
RUN cp autodriver /usr/bin/autodriver
RUN chmod +s /usr/bin/autodriver

# Clean up
WORKDIR /home
RUN apt-get remove -y git
RUN apt-get -y autoremove
RUN rm -rf Tango/

# Check installation
RUN ls -l /home
RUN which autodriver
