![Kotlin & Gradle](https://github.com/Thiamath/yfnsoche/workflows/Kotlin%20&%20Gradle/badge.svg)
# yfnsoche
Anonymized code challenge

## Run the code
To run the application locally:
1. Clone the repo
2. Run in the project root directory:
```cmd
# Windows
C:/..../yfnsoche> gradlew bootrun

# Linux & Mac
$/..../yfnsoche> chmod +x gradlew
$/..../yfnsoche> ./gradlew bootrun
```

### Using the app
#### GET payslips
To get the payslips for December 2018 you can run:
```cmd
curl --location --request GET 'localhost:8080/payslips.201812.txt'
```
or use any software like Postman to run a `GET` request like the above.

#### PUT a new tax rate
To modify the tax rate for December 2018 to 15% you can run:
```cmd
curl --location --request PUT 'localhost:8080/tax' \
--header 'Content-Type: application/json' \
--data-raw '{
  "taxValue": 15.0,
  "year": 2018,
  "month": 12
}'
```
To clear the tax rate and use the legacy one run:
```cmd
curl --location --request PUT 'localhost:8080/tax' \
--header 'Content-Type: application/json' \
--data-raw '{
  "taxValue": null,
  "year": 2018,
  "month": 12
}'
```
Using null as clear value. You can also omit the `taxValue` field and will work likewise.

After change the taxes, run the GET request again to see the changes.
