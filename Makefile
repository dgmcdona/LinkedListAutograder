SHELL:=/bin/bash

all:
	/usr/bin/unzip -j -qq handin.zip
	/usr/bin/python3 grader.py

#tar -mxf autograde.tar
