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
KLV is not properly implemented to make and parse messages correctly. Currently our clients work with our server and can connect to other servers. However since KLV is not implemented correctly our messages cannot be parsed by other servers properly following KLV. Meaning other servers can see our connection and exit but they cannot see messages that we send and we cannot see messages they send. 

Currently there is still the problem that multiple users can join the server with the same user name. 

