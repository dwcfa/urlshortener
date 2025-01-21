# urlshortener
Shortens URL's 

### Build Directions
Runs on Java 11
Requires Maven and Lombok
```
mvn clean install
```

This application has 2 method calls.
* POST to /encode to save the url 
* GET to retrieve the saved url /decode

Here is a post to encode that can ran command line or imported into postman.
```
wget --no-check-certificate --quiet \
  --method POST \
  --timeout=0 \
  --header 'Content-Type: application/json' \
  --body-data '{
    "url": "https://www.hey.com/"
}' \
   'http://localhost:8080/encode'
```
JSON structure for the POST above to http://localhost:8080/encode
```
{
    "url": "https://www.yoururl.com/"
}
```

Here is a sample retrieve (Note adjust the key value to the one returned from above)
```
wget --no-check-certificate --quiet \
  --method GET \
  --timeout=0 \
  --header '' \
   'http://localhost:8080/decode?key=D2NuTn'

```
URL Request.(Again change key)
```
http://localhost:8080/decode?key=D2NuTn
```