#!/usr/bin/env python3
"""
$Description$<
"""

__author__ = "Aollio Hou"
__email__ = "aollio@outlook.com"


def write_to_file(file, cls_name):
    file.write('package com.nightcat.repository;\n\n')
    file.write('import com.nightcat.entity.%s;\n' % cls_name)
    file.write('import org.springframework.stereotype.Repository;\n\n')
    file.write('@Repository\n')
    file.write('public class %sRepository extends AbstractDao<%s> {' % (cls_name, cls_name))
    file.write('}')


import os
from os import path

for filename in os.listdir('entity'):
    if filename.endswith('java'):
        cls = filename[:-5]
        with open(path.sep.join(['repository', cls + 'Repository.java']), 'w+') as target:
            write_to_file(target, cls)
            target.flush()

