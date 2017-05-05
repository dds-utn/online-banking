


## Crear el assembly

```
mvn package
```


## Ejecutar el assembly 

```
java -jar target/online-banking-0.0.1-SNAPSHOT-jar-with-dependencies.jar 
```

## Cronear el assembly 

```
sudo crontab -e
# Agregar lo siguiente:
#   * *   *   *  *    java -jar <PATH>/online-banking/java/target/online-banking-0.0.1-SNAPSHOT-jar-with-dependencies.jar >> <PATH>/out.txt 
```
