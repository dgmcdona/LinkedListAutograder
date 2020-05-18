#!/usr/bin/python3

import sys
import json
from subprocess import check_output, PIPE, CalledProcessError

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


def compile_test(test_class):
    process = 'javac {} {}.java'
    process = process.format(CPFLAGS, test_class)
    try:
        check_output(process, shell=True)
        return 1
    except CalledProcessError:
        print("Could not compile {}".format(test_class))
        return 0


def compile_all_tests():
    for test in TESTS:
        if not compile_test(test):
            print("Error compiling test classes: Exiting")
            sys.exit(2)


def compile_class(_class, scores):
    process = 'javac {} {}.java'
    process = process.format(CPFLAGS, _class)
    try:
        check_output(process, shell=True)
        return True
    except CalledProcessError:
        print("Error compiling {}.java, check submission.".format(_class))
        return False


def test_all_classes():
    scores = {}
    for _class in POINTS:
        if not compile_class(_class, scores):
            c_scores = {test: 0 for (test, points) in POINTS[_class].items()}
            scores.update(c_scores)
        else:
            scores.update(run_test(_class))
    return scores


def run_test(_class):
    scores = {}
    try:
        process = 'java {} org.junit.runner.JUnitCore {}'
        process = process.format(CPFLAGS, 'Test' + _class)
        out = check_output(process, shell=True)
        point_values = POINTS[_class]
        for test_case in point_values:
            if test_case in out.decode('utf-8'):
                scores[test_case] = 0
            else:
                scores[test_case] = point_values[test_case]
        return scores
    except CalledProcessError:
        return {key: 0 for (key, value) in point_values.items()}


def print_scores():
    print(json.dumps(test_all_classes()))


def main():
    compile_all_tests()
    print_scores()


if __name__ == '__main__':
    main()
