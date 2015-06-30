# Python script to execute multiple processes and collect their results in a single file
# by Alberto Tonda, 2014 <alberto.tonda@grignon.inra.fr>

import argparse
import os

# NOTE "global" variables; modify here to change the behavior of the script
#scriptCommand = "python fitness.py" 	# name of the script, e.g. "python script.py"
scriptCommand = "python fitness.py"    # name of the script, e.g. "python script.py"
inputOption = "--input "	# options of the script
outputOption = "--output "
otherOptions = "" 

childOutputFileBase = "fitness"		# the base name for the children's output files (they will become fitness1, fitness2, etc.)

defaultOutputFile = "fitness.out" # the default name for the output file produced by this script

# ok, as input we need
# - name of the output file
# - list of arguments to pass to each child

parser = argparse.ArgumentParser()

parser.add_argument("--output", type=str, help="Name of the output file, where the results of all children will be written to. By default, it's " + defaultOutputFile) 

parser.add_argument("--input", metavar='N', type=str, nargs='+', required=True, help="Input filenames. Each one will be passed to a child.")

args = parser.parse_args()

# check if a different output file was specified
if args.output != None : defaultOutputFile = args.output

# some useful variables
outputFilesList = []
pidList = []

# now, for each argument passed as "input", create a child and execute a command
for i in range(0, len(args.input)) :
	
	# input and output for the child
	childInputFile = args.input[i]
	childOutputFile = childOutputFileBase + str(i)
	
	# fork the process
	newpid = os.fork()

	if newpid == 0 :
		# child
		# create command
		command = scriptCommand + " " + inputOption + childInputFile + " " + outputOption + childOutputFile
		# execute it
		#with open(childOutputFile, "w") as fp : fp.write( command + "\n" )
		os.system(command)
		os._exit(0)
	else :
		# father
		# store the PID and the filename
		outputFilesList.append( childOutputFile )
		pidList.append( newpid )

# wait for all child processes to exit
for pid in pidList :
	os.waitpid( pid, 0 ) # TODO check for possible errors returned by the child process

# write final output file
with open(defaultOutputFile, "w") as fp :
	
	for outputFile in outputFilesList :
		# read the file
		lines = []
		with open(outputFile, "r") as fpChild : lines = fpChild.readlines()
		
		# copy the file content to the new file
		fp.write(lines[0].rstrip() + "\n")
		
		# delete the temporary file
		os.remove( outputFile )

# end
