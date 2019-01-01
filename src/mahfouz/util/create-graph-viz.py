#!/usr/bin/python
#

# coding: utf8
# read a graph in the form of pairs of edges
# and output it in the format suitable for visjs

import sys
import re

infile = open('in.txt')
outfile = open('out.txt', 'w')

# output nodes in the form
# {id: 1, label: '1'},
for i in range(0, 19):
    print "{id: " + str(i) + ", label: '" + str(i) + "'},"

# out edges in the form of:
#    {from: 1, to: 3},

line = infile.readline()
while line:
    parts = line.strip().split(" ", 2)
    src = int(parts[0]) - 1
    dst = int(parts[1]) - 1
    outfile.write("{ from: " + str(src) + ", to: " + str(dst) + ", arrows:'to'},\n")
    line = infile.readline()

infile.close()
outfile.close()

