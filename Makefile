SHELL:=/bin/bash

all:
	tar -mxf autograde.tar
	/usr/bin/unzip -j -qq handin.zip
	/usr/bin/python2.7 grader.py
