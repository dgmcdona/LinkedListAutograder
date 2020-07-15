#!/usr/bin/python3

import sys
import os
import json
from subprocess import check_output, CalledProcessError

# Author: David McDonald (5/15/20)

# Summary: Build and test all Java files.
# Usage: python3 grader.py

# set the java CLASSPATH to include junit and hamcrest .jar files
CPFLAGS = '-cp .:libs/hamcrest-all-1.3.jar:libs/junit-4.13.jar'

# List of test class names w/o file extensions.
TESTS = ['TestSinglyLinkedList',
         'TestMyStack',
         'TestInfixPostfix']

# Nested dictionary assigning point values to tests.
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

# Prepare dictionary for semantic feedback
'''
semantic_feedback = dict()
semantic_feedback['_presentation'] = 'semantic'
semantic_feedback['stages'] = ['Build', 'Run', 'Scoring']
semantic_feedback['Build'] = {
        'Compile tests': {'passed' = False},
        'Compile classes': {'passed' = False},
        }
'''


# Compile test class, return True if successful
def compile_test(test_class):
    process = 'javac {} {}.java'
    process = process.format(CPFLAGS, test_class)
    try:
        check_output(process, shell=True)
        return True
    except CalledProcessError:
        print("Could not compile {}".format(test_class))
        return False


# If all test classes do not compile sucessfully, clean and exit.
def compile_all_tests():
    for test in TESTS:
        if not compile_test(test):
            print("Error compiling test classes: Exiting")
            clean_and_exit(2)


# Compile java class file submitted by student, return True if successful.
def compile_class(_class, scores):
    process = 'javac {} {}.java'
    process = process.format(CPFLAGS, _class)
    try:
        check_output(process, shell=True)
        return True
    except CalledProcessError:
        print("Error compiling {}.java, check submission.".format(_class))
        return False


# Test all student-submitted classes. If class compilation fails, assign zeros.
# Else, update scores dictionary with scores returned by run_test function.
# Return scores when done.
def test_all_classes():
    scores = {}
    for _class in POINTS:
        if not compile_class(_class, scores):
            c_scores = {test: 0 for (test, points) in POINTS[_class].items()}
            scores.update(c_scores)
        else:
            scores.update(run_test(_class))
    return scores


# If a junit test returns with code 0, all tests were passed, and full points
# are returned from the values in the scores dictionary. 
# If exit code is 1, parse failed tests from the output and return scores.
# Else, something else went wrong with this test, return all zero scores for 
# the unit tests for the current class.
def run_test(_class):
    scores = {}
    try:
        process = 'java {} org.junit.runner.JUnitCore {}'
        process = process.format(CPFLAGS, 'Test' + _class)
        check_output(process, shell=True)
        scores = POINTS[_class]
        return scores
    except CalledProcessError as err:
        if err.returncode == 1:
            point_values = POINTS[_class]
            for test_case in point_values:
                if test_case in err.output.decode('utf-8'):
                    scores[test_case] = 0
                else:
                    scores[test_case] = point_values[test_case]
            return scores
        else:
            return {key: 0 for (key, value) in point_values.items()}


# Print JSON representation of the scores.
def print_scores(is_semantic):
    if is_semantic:
        print('{"_presentation": "semantic"}')
    results = test_all_classes()
    scores = {'scores': results}
    scores['scoreboard'] = list(results.values())
    print(json.dumps(scores))


# Remove all class and build files, and exit.
def clean_and_exit(status):
    os.system('rm *.java')
    os.system('rm *.class')
    sys.exit(status)


def main():
    compile_all_tests()
    print_scores(True)
    clean_and_exit(0)


if __name__ == '__main__':
    main()
