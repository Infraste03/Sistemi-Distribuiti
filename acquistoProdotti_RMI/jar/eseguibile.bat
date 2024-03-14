
start java -jar "%~dp0Server_jar\serverCallbacks.jar"
timeout 1
start java -jar "%~dp0Client_jar\clientCallbacks.jar"
timeout 3
start java -jar "%~dp0Client_jar\clientCallbacks.jar"
timeout 3
start java -jar "%~dp0Client_jar\clientCallbacks.jar"