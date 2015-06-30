#!/usr/bin/python
#-*- coding latin-1-*-

# chemin=chemin2=0
# while chemin==chemin2:
	# print("chemin du fichier a lire: ")
	# chemin = input ()
	# print("chemin du fichier a ecrire: ")
	# chemin2=input ()

#a_remplacer=input("cahaine a remplacer")
#a_remplacer=list(a_remplacer)
import sys
f=file(str(sys.argv[2]), "w")

count=1
for ligne in file(str(sys.argv[1]), "r"):
	if ligne.find("case_replace") != -1: #verifi si la string et dans la ligne
		ligne=ligne.replace("case_replace",str(count))
		count=count+1
	f.write(ligne)
	
	
f.close()


