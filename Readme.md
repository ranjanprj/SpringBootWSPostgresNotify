POC to show WebSocket updates in SpringBoot using  Postgres Listen/Notify

1. Have an Postgresql running.
2. Change the connection parameters in PGConn.java file.
3. Run the NotificationTest.java to generate some NOTIFY signals to Postgres.
4. Run the Application.java file to start SprintBoot
5. Go over to http://localhost:8080/ and press connect


![PG Listen/Notify Websocket](https://github.com/ranjanprj/SpringBootWSPostgresNotify/blob/master/src/main/resources/static/Springboot%20WS%20PG.gif"SpringBoot", PG Listen/Notify Websocket")


