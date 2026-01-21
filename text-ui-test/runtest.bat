@ECHO OFF
SETLOCAL

REM go to project root
cd ..

REM create bin directory if it doesn't exist
if not exist bin mkdir bin

REM delete output from previous run
if exist text-ui-test\ACTUAL.TXT del text-ui-test\ACTUAL.TXT

REM compile all java files recursively into bin
javac -Xlint:none -d bin ^
 src\main\java\shallowseek\*.java ^
 src\main\java\shallowseek\commands\*.java ^
 src\main\java\shallowseek\tasks\*.java ^
 src\main\java\shallowseek\exceptions\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program
java -classpath bin shallowseek.ShallowSeek ^
 < text-ui-test\input.txt ^
 > text-ui-test\ACTUAL.TXT

REM compare the output
FC text-ui-test\ACTUAL.TXT text-ui-test\EXPECTED.TXT
pause

ENDLOCAL