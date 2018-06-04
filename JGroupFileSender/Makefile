DIRECTORIO_SENDER=./build/classes/
PREFIJO_SENDER=com/gomezoscar/jgroupfilesender/sender/
PREFIJO_UTILS=com/gomezoscar/jgroupfilesender/utils/
RESOURCES=resources
CONSOLE_RECEIVER=ConsoleReceiver.jar
CONSOLE_SENDER=ConsoleSender.jar
GUI_RECEIVER=GuiFileReceiver.jar
all: consolereceiver consolesender guireceiver


consolereceiver:
	 rm $(CONSOLE_RECEIVER); jar -cvfe $(CONSOLE_RECEIVER) com.gomezoscar.jgroupfilesender.sender.ConsoleFileReceiver  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)ConsoleFileReceiver.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)FileReceiver.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)UDPProcessor.class \
		-C $(DIRECTORIO_SENDER) $(RESOURCES) \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)ObserverWithPrint.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Constants.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPOutputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPInputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Utils.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Observer.class ; \
		java -jar $(CONSOLE_RECEIVER)  


consolesender:
	 rm $(CONSOLE_SENDER); jar -cvfe $(CONSOLE_SENDER) com.gomezoscar.jgroupfilesender.sender.ConsoleFileSender  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)ConsoleFileSender.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)FileSender.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)UDPProcessor.class \
		-C $(DIRECTORIO_SENDER) $(RESOURCES) \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)ObserverWithPrint.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Constants.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPOutputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPInputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Utils.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Observer.class ; \
		java -jar $(CONSOLE_SENDER)  


guireceiver:
	 rm $(GUI_RECEIVER); jar -cvfe $(GUI_RECEIVER) com.gomezoscar.jgroupfilesender.sender.GuiFileReceiver  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)ConsoleFileReceiver.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)FileReceiver.class \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_SENDER)UDPProcessor.class \
		-C $(DIRECTORIO_SENDER) $(RESOURCES) \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)ObserverWithPrint.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Constants.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPOutputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)UDPInputStream.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Utils.class  \
		-C $(DIRECTORIO_SENDER) $(PREFIJO_UTILS)Observer.class ; \
		java -jar $(GUI_RECEIVER)  

