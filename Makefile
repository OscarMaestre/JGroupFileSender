PAQUETE=com/gomezoscar/jgroupfilesender/sender
UTILS=com/gomezoscar/jgroupfilesender/utils
DIRECTORIO_CLASES_CONSOLE_FILE_RECEIVER=./bin
all: utils resto

utils:
	cd src ; javac $(UTILS)/*.java -d ../bin ; cd ..
resto:
	cd src ; javac $(PAQUETE)/*.java -d ../bin ; \

