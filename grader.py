#!/usr/bin/python3

import subprocess
import os
import sys

# Author: David McDonald (5/15/20)

# Summary: Build and test all Java files.
# Usage: python3 grader.py
CPFLAGS = '-cp .:libs/hamcrest-all-1.3.jar:libs/junit-4.13.jar'

CLASSES = ['SinglyLinkedList', 'MyStack', 'InfixPostfix']
TESTS = ['TestSinglyLinkedList',
         'TestMyStack',
         'TestInfixPostfix']
POINTS = {'SinglyLinkedList': {
              'testAdd': 4,
              'testInsertAt': 3,
              'testRemove': 3,
              'testClear': 2,
              'testIsEmpty': 2,
              'testSize': 2,
              'testGetNthFromFirst': 4,
              'testGetNthFromLast': 4,
              'testIterator': 3,
              'testToString': 3,
              'testLinkedListIterator': 10
              },
          'MyStack':
              {
              'testPop': 10,
              'testPush': 10,
              'testStackIsEmpty': 10,
              },
          'InfixPostfix':
              {
              'testInfixPostfix': 20
              }
          }


def compile_java(filename, files):
    if '{}.java'.format(filename) not in files:
        message = ('Missing {}.java -- '
                   'Add file to handin.zip or double-check file name.')
        message = message.format(filename)
        print(message)
    else:
        try:
            process = 'javac {}.java'.format(filename)
            subprocess.check_output(process, shell=True)
        except subprocess.CalledProcessError as javac_error:
            print("Could not compile {}.java: Exited with Error {}: {}".format(
                filename, javac_error.returncode, javac_error.output))


def compile_tests():
    try:
        process = 'javac {} {}.java'
        tests = " ".join([x + '.java' for x in TESTS])
        process = process.format(CPFLAGS, tests)
        subprocess.check_output(process)
    except subprocess.CalledProcessError as javac_error:
        print("Could not compile Junit tests")
        print(javac_error.returncode, javac_error.output)
        sys.exit(0)


def run_tests():
    out = ''
    for test in TESTS:
        try:
            process = 'java org.junit.runner.JUnitCore {}'
            process = process.format(test)
            out += subprocess.check_output(process, shell=True)
        except subprocess.CalledProcessError as java_error:
            print("Error running unit test: {}".format(test))
            print(java_error.returncode, java_error.output)
    return out


def main():
    scores = []
    ls_files = os.listdir('.')
    compile_tests()
    for _class in CLASSES:
        compile_java(_class, ls_files)
    output = run_tests()
    for test in POINTS:
        if test in output:
            scores.append(0)
        else:
            scores.append(POINTS[test])


if __name__ == '__main__':
    main()
