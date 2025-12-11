# Chatroom Project
## Brief Description
Final project for Westminster CMPT 352 Fall Semester 2025. This project is a chat room with design based around a central sever that clients connect to and interact with other connected clients.
This chat room does not support private messaging and only has one centralized group chat. Files included are for sever and client. 

## Authors
Jack Brinkman & Heidi Andre

## Language
Java

## Compile Files
Compile all files in both folders for best results.
Example:

`cd client `
`javac *.java `

and 

`cd server`
`javac *.java`

## Running Server
`cd server`
`java Server`

## Running Clients
`cd clients`
`java MyGUI`
Fill in the prompt boxes accordingly and press join button to connect to Server.

## Known Issues and Limitations
KVL is not fully properly implemented to make and parse messages correctly. Currently our clients work with our server and can connect to other servers in the working branch. Messages in the main branch currently are encoded in KVL. However currently our server can't handle that since KVL has not been fully implemented there. 

Clients exiting still needs to be tested to see if it is handling closing all of the connections correctly.

