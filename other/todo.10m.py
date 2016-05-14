#!/usr/bin/env python

import os
import subprocess

JARPATH = os.path.join(os.path.abspath(os.path.dirname(__file__)), '..', 'lambdasloth.todo.jar')


def run_jar_file():
    cmd_args = ['java', '-jar', JARPATH]
    subprocess.call(cmd_args)

if __name__ == '__main__':
    run_jar_file()
