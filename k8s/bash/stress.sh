#!/bin/bash
for i in {1..10000}; do
    curl http://192.168.49.2:30080
    seleep $1
done