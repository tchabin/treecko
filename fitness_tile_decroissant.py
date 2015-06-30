# Simple script to run fitness evaluation for the individuals in 2048
# but with some command-line parameters

import argparse
import os
import subprocess
import sys

#DEBUG = True
DEBUG = False

# parse some command-line arguments
parser = argparse.ArgumentParser()
parser.add_argument("--input", required=True)
parser.add_argument("--output", required=True)

args = parser.parse_args()

if DEBUG :
	print "Input file: \"" + args.input + "\""
	print "Output file: \"" + args.output + "\""

individualName = args.input
fitnessFileName = args.output

# create a temporay directory, copy all the relevant files inside it and move there
individualDir = individualName + "-dir"
os.system("mkdir " + individualDir)
os.system("cp replace.py 2048.jar " + individualName + " " + individualDir)
os.chdir(individualName + "-dir")

# compile and execute: every line is basically what you would write on the terminal, only inside the os.system() system call
os.system("./replace.py " + individualName + " MyAgent.java")
os.system("javac -cp 2048.jar MyAgent.java")
os.system("jar -cf MyAgent.jar MyAgent.class")

# subprocess is used to capture the stdout inside a string
stdOutput = subprocess.Popen("java -jar 2048.jar MyAgent.jar MyAgent 2000 1.0 123", stdout = subprocess.PIPE, #stderr = subprocess.STDOUT, 
shell=True).communicate()[0]

if DEBUG :
	print "The captured standard output is: \"" + stdOutput + "\""

# parse the two lines
lines = stdOutput.rstrip().split("\n")
header = lines[0].rstrip().split(',')
values = lines[1].rstrip().split(',')

# create a hash map with the headers
fitnessValues = dict()
for i in range(0, len(header)) :
	fitnessValues[header[i]] = float(values[i])
	
if DEBUG : print "The dictionary is:", fitnessValues

# write the fitness file
with open(fitnessFileName, "w") as fp :
	if fitnessValues["AvgActionTime[ms]"] > 0.9 :
		t=(-float(fitnessValues["AvgActionTime[ms]"])/0.1+10)
		fp.write(str(fitnessValues["65536"]*t) + " " + str(fitnessValues["32768"]*t) + " " + str(fitnessValues["16384"]*t) + " " + str(fitnessValues["8912"]*t) + " " + str(fitnessValues["4096"]*t) + " " + str(fitnessValues["2048"]*t)+ " " + str(fitnessValues["1024"]*t)+ " " + str(fitnessValues["512"]*t)+" " + str(fitnessValued["256"]*t)+ " " +str(fitnessValues["128"]*t)+" " + str(fitnessValues["64"]*t)+" " + str(fitnessValues["32"]*t)+" " + str(fitnessValues["16"]*t) )

	else :
		fp.write(str(fitnessValues["65536"]) + " " + str(fitnessValues["32768"]) + " " + str(fitnessValues["16384"]) + " " + str(fitnessValues["8912"]) + " " + str(fitnessValues["4096"]) + " " + str(fitnessValues["2048"])+ " " + str(fitnessValues["1024"])+ " " + str(fitnessValues["512"])+" " + str(fitnessValues["256"])+ " " +str(fitnessValues["128"])+" " + str(fitnessValues["64"])+" " + str(fitnessValues["32"])+" " + str(fitnessValues["16"]) )

# copy the fitness file to the parent directory
os.system("cp " + fitnessFileName + " ..")

# remove all temporary files
os.chdir("..")
os.system("rm -r " + individualDir)
